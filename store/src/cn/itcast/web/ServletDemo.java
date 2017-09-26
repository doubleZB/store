package cn.itcast.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo
 */
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 请求转发到注册页面
		request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		
		// 一个需求一个servlet太麻烦,因为一个模块下要有很多的功能,那牛需要写很多的servlet,这样servlet写的就太频繁了
		// 问: 有没有什么更好的技术来解决这样的方案问题?
		// 解决方案:  一个模块对应一个sevlet
		// 怎么样让一个模块的所有功能都去找这个servlet的方法实现呢?  传参
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
