package cn.itcast.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.domain.CateGory;
import cn.itcast.domain.Product;
import cn.itcast.service.CategoryService;
import cn.itcast.utils.FactoryDemo;
import cn.itcast.utils.UUIDUtils;

/**
 * Servlet implementation class AddServlet
 */
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 创建磁盘文件工厂
			DiskFileItemFactory disk = new DiskFileItemFactory();
			// 生成上传核心对象
			ServletFileUpload sfu = new ServletFileUpload(disk);
			// 解析request
			List<FileItem> list = sfu.parseRequest(request);
			Map<String,Object> map=new HashMap<String,Object>();
			
			// 循环
			for (FileItem item : list)
			{
				// 判断
				if(item.isFormField())
				{
					// 普通的
					String name = item.getFieldName();  
					String value=item.getString("utf-8");
					map.put(name, value);
				}else
				{
					// 文件的
					String fileName = item.getName();  //文件名
					InputStream is = item.getInputStream(); // 内容
					// 获取地址
					ServletContext scon = this.getServletContext();
					String path = scon.getRealPath("");
					FileOutputStream os = new FileOutputStream(path+"/upload/"+fileName);
					
					map.put("pimage", "upload/"+fileName);
					
					IOUtils.copy(is, os);
				}
			}
			
			// 将map的数据给product
			Product p = new Product();
			BeanUtils.populate(p,map);
			
			p.setPid(UUIDUtils.getUUID());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(new Date());
			p.setPdate(date);
			p.setPflag(0);
			
			CateGory cg = new CateGory();
			cg.setCid(map.get("cid").toString());
			p.setCateGory(cg);
			
			CategoryService cs=(CategoryService)FactoryDemo.getBean("CategoryService");
			
			cs.saveProduct(p);
			
			response.getWriter().print("ok");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
