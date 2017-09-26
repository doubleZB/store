package cn.itcast.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.serviceimpl.ProductServiceImpl;
import cn.itcast.utils.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 分页商品的展示
	public String findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			// 获取分类的ID
			String cid = request.getParameter("cid");
			// 获取当前页数
			int pageNumber =Integer.parseInt(request.getParameter("pageNumber"));
			// 设定每页显示的条数
			int pageSize=12;
			
			// 带着参数调用service
			ProductService ps=new ProductServiceImpl();
			PageBean pb = ps.findPage(cid,pageNumber,pageSize);
			request.setAttribute("pb", pb);
			request.setAttribute("cid", cid);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "商品查询失败,请等待稍后查询");
			return "/jsp/info.jsp";
		}
		
		return "/jsp/product_list.jsp";
	}

	
	// 单个商品的展示
	public String findBypid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			
			// 1 获取点击商品的pid
			String pid = request.getParameter("pid");
			// 2 传递pid给service
			ProductService ps=new ProductServiceImpl();
			Product p = ps.findBypid(pid);
			request.setAttribute("p", p);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "商品查询失败,请等待稍后查询");
			return "/jsp/info.jsp";
		}
		
		
		return "/jsp/product_info.jsp";
	}
	
	// 查最新商品和热门商品
	public String findByhot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			ProductService ps=new ProductServiceImpl(); 
			// 获取最新商品
			List<Product> newList = ps.findNew();
			// 获取热门商品
			/*ps.findHot();*/
			// 封装最新商品
			request.setAttribute("newList", newList);
			// 封装热门商品
			//作业
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "商品查询失败,请等待稍后查询");
			return "/jsp/info.jsp";
		}
		
		// 到首页展示
		return  "/jsp/index.jsp";
		
	}
}
