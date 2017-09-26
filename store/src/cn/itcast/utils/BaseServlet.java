package cn.itcast.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 为所有模块执行反射代码
		try {
			Class clazz = this.getClass();
			String me = request.getParameter("method");
			Method method = clazz.getMethod(me, HttpServletRequest.class,HttpServletResponse.class);
			//让方法自己执行
			System.out.println(me);
			String value =(String)method.invoke(clazz.newInstance(), request,response);
			// 判断
			if(value!=null)
			{
				// 做请求转发
				request.getRequestDispatcher(value).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "反射发生了错误");
			request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
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
