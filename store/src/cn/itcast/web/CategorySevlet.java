package cn.itcast.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.CateGory;
import cn.itcast.service.CategoryService;
import cn.itcast.serviceimpl.CategoryServiceImpl;
import cn.itcast.utils.BaseServlet;

/**
 * Servlet implementation class CategorySevlet
 */
public class CategorySevlet extends BaseServlet{
	private static final long serialVersionUID = 1L;

	public String findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			/*response.setCharacterEncoding("utf-8");*/
			response.setContentType("text/html;charset=utf-8");
		try {
			// 调用service
			CategoryService cs=new CategoryServiceImpl();
			// 从mysql中获取的
			String json = cs.findCateogry();
			// 从reids中获取
			/*String json =cs.findCateogryByRedis();*/
			
			response.getWriter().println(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "导航条数据加载失败");
			return "/jsp/info.jsp";
		}
		
		return null;
	}


}
