package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.ProductService;
import cn.itcast.serviceimpl.ProductServiceImpl;
import cn.itcast.utils.BaseServlet;
import cn.itcast.utils.FactoryDemo;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			response.setContentType("text/html;charset=utf-8");
			int pageNumber = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			// 调用service
			ProductService ps=(ProductService)FactoryDemo.getBean("ProductService");
			String json = ps.findProduct(pageNumber,pageSize);
			System.out.println(json);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return null;
	}

	public String findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			response.setContentType("text/html;charset=utf-8");
			ProductService ps=(ProductService)FactoryDemo.getBean("ProductService");
			String json = ps.findCategory();
			System.out.println(json);
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return null;
	}
	
}
