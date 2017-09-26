package cn.itcast.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class ServletDemo
 */
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			/*String username = request.getParameter("username");
			String upload = request.getParameter("upload");
			System.out.println(username+":"+upload);
			
			request.getInputStream(); // 工作中不会使用,因为太复杂
*/
		
			// 使用上传工具包
		
			try {
				
				// 1 创建一个磁盘文件工厂---需要通过它产生核心上传对象
				DiskFileItemFactory disk = new DiskFileItemFactory();
				// 2 生成核心的上传对象---需要它解析request
				ServletFileUpload sfu = new ServletFileUpload(disk);
				// 3 解析request
				List<FileItem> list = sfu.parseRequest(request);
				// 4 遍历所有的表单项
				for (FileItem item : list) 
				{
					// 判断是普通的表单项还是上传的表单项 item.isFormField(): true:普通的表单项 false:上传的表单项 
					if(item.isFormField())
					{
						// 普通的表单项  name value
						String name=item.getFieldName();  //username
						/*String value=item.getString();*/   // zhangsan
						String value=item.getString("utf-8");
						System.out.println(name+":"+value);
					}else
					{
						// 上传表单项  文件名  内容
						String filename = item.getName();  // cs.txt
						InputStream is = item.getInputStream(); //文件内容
						
						// 放在服务器上
						String path = this.getServletContext().getRealPath("");
						System.out.println(path);
						FileOutputStream os = new FileOutputStream(path+"/upload/"+filename);
						
						IOUtils.copy(is, os);
						
					}
				}
				
				
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
