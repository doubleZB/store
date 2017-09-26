package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.utils.BaseServlet;
import cn.itcast.utils.FactoryDemo;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 获取购物车
	public  Cart getCart(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		// 先从session中获取
		Cart cart =(Cart)session.getAttribute("cart");
		if(cart==null)
		{
			// 没有 --创建一个放到session中 然后再返回
			cart = new Cart();
			session.setAttribute("cart", cart);	
		}
		// 有--直接返回
		return cart;
	}
	
	// 对购物车添加购物项
	public String addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			String pid = request.getParameter("pid");
			// 根据商品pid获取对应的商品对象
			ProductService ps =(ProductService)FactoryDemo.getBean("ProductService");
			Product product = ps.findBypid(pid);
			int count = Integer.parseInt(request.getParameter("count"));
			
			// 创建一个购物项对象
		    CartItem item = new CartItem();
		    item.setCount(count);
		    item.setProduct(product);
			// 创建一个购物车对象 (唯一)
		    Cart cart = getCart(request);
			// 把购物项放在购物车中
		    cart.add(item);
		    
		    // 重定向到cart.jsp
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	// 对购物车进行清空
	public String removeAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cart cart = getCart(request);
		cart.removeAll();
		// 重定向到cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
	// 对购物车的某个购物项进行删除
	public String removeByid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String key = request.getParameter("id");
		Cart cart = getCart(request);
		cart.remove(key);
		// 重定向到cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	
}
