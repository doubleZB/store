package cn.itcast.web;

import java.io.IOException;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.CateGory;
import cn.itcast.service.CategoryService;
import cn.itcast.serviceimpl.CategoryServiceImpl;
import cn.itcast.utils.BaseServlet;
import cn.itcast.utils.FactoryDemo;
import cn.itcast.utils.UUIDUtils;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			response.setContentType("text/html;charset=utf-8");
		try {
			
			// 当前页
			int pageNumber =Integer.parseInt(request.getParameter("page"));
			// 每页显示的条数
			int pageSize =Integer.parseInt(request.getParameter("rows"));
			
			// 调用service
			CategoryService cs=(CategoryService)FactoryDemo.getBean("CategoryService");
			String json = cs.findCategory(pageNumber,pageSize);
			System.out.println(json);
			response.getWriter().println(json);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	public String  delBycid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			
			String cid = request.getParameter("cid");
			CategoryService cs=(CategoryService)FactoryDemo.getBean("CategoryService");
			cs.delBycid(cid);
			
			response.getWriter().print("ok");
		} catch (Exception e) {
			
		}
		
		
		return null;
	}
	
	
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			
			String cname = request.getParameter("cname");
			CateGory cg = new CateGory();
			cg.setCname(cname);
			cg.setCid(UUIDUtils.getUUID());
			CategoryService cs=(CategoryService)FactoryDemo.getBean("CategoryService");
			cs.save(cg);
			
			response.getWriter().print("ok");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			CateGory cg = new CateGory();
			cg.setCname(cname);
			cg.setCid(cid);
			CategoryService cs=(CategoryService)FactoryDemo.getBean("CategoryService");
			cs.update(cg);
			
			response.getWriter().print("ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
}
