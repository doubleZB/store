package cn.itcast.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.serviceimpl.UserServiceImpl;
import cn.itcast.utils.BaseServlet;
import cn.itcast.utils.MailUtils;
import cn.itcast.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 先获取参数
		String method = request.getParameter("method");
		// 判断
		if("registUI".equals(method))
		{
			 // 去找注册页面--方法
			registUI(request,response);
			
		}else if("loginUI".equals(method))
		{
			// 去找登录页面--方法
			loginUI(request,response);
			
		}else if("regist".equals(method))
		{
			// 实现注册功能的方法
			
		}
		// 通过传参+if else的方式解决了一个模块对应一个servlet,但是发现需要写很多的if else,那这样就不利于项目的扩展
		// 问: 有没有更好的技术去解决?
		// 解决技术: 反射!!!  
		
		
		try {
			Class clazz = this.getClass();
			String me = request.getParameter("method");
			Method method = clazz.getMethod(me, HttpServletRequest.class,HttpServletResponse.class);
			//让方法自己执行
			System.out.println(me);
			method.invoke(clazz.newInstance(), request,response);
		} catch (Exception e) {
			request.setAttribute("msg", "反射发生了错误");
			request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		
		
		
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

	
	
	// 自己的方法
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//跳到注册页面
		/*request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);*/
		return "/jsp/register.jsp";
	}
	
	public String  loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//跳到登录页面
		/*request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);*/
		return "/jsp/login.jsp";
	}
	
	// 注册方法
	public String regist(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			
			// 获取页面的数据
			Map<String, String[]> map = request.getParameterMap();
			// 将map的数据封装到javabean中
			User user = new User();
			BeanUtils.populate(user, map);
			// 设置uid (uuid)
			user.setUid(UUIDUtils.getUUID());
			// 设置激活状态
			user.setState(0);
			// 设置激活码  uuid+uuid(唯一)
			user.setCode(UUIDUtils.getUUID()+UUIDUtils.getUUID());
			
			// 带着user调用service
			UserService us=new UserServiceImpl();
			us.regist(user);
			// 发邮件
			String emailMsg="这是一封给"+user.getName()+"用户的激活邮件,请<a href=http://localhost:8080/store/user?method=active&code="+user.getCode()+">点击激活</a>";
			MailUtils.sendMail(user.getEmail(), emailMsg);
			
			// 告诉用户去登录邮件激活
			request.setAttribute("msg", "恭喜你,注册成功,请尽快登录邮箱进行激活...");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "注册失败");
			return "/jsp/info.jsp";
		}
		
		return "/jsp/info.jsp";
	}
	
	// 激活
	public String active(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			// 接收激活码
			String code = request.getParameter("code");
			// 根据激活码查询数据库还有没有该用户
			UserService us=new UserServiceImpl();
			User user = us.findByCode(code);
			// 判断user是否为null
			if(user==null)
			{
				// 没有--告诉用户重新去注册
				request.setAttribute("msg", "亲,激活失效请重新注册");
				return "/jsp/info.jsp";
			}
			// 有--修改激活状态为1
			user.setState(1);
			us.update(user);
			// 告诉用户激活成功,去登录
			request.setAttribute("msg", "亲,激活成功,请去登录吧");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "激活失败");
			return "/jsp/info.jsp";
		}
		
		return "/jsp/info.jsp";
	}

	// 登录功能
	public String login(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			
			// 获取用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			// 根据用户名和密码去数据库匹配
			UserService us=new UserServiceImpl();
			User user = us.findUser(username,password);
			if(user==null)
			{
				request.setAttribute("msg", "用户名或者密码错误..");
				return "/jsp/login.jsp";
			}
			// 不为null 判断激活状态是否为1
			if(user.getState()!=1)
			{
				request.setAttribute("msg", "亲,你还没有激活,请先去邮箱激活");
				return "/jsp/info.jsp";
			}
			
			// 登录成功 到首页展示
			session.setAttribute("user", user);
			
			// 重定向到首页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			return "/jsp/info.jsp";
		}
		
		
		
		return null;
	}

	// 用户退出功能实现
	public String quit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// 获取session
		HttpSession session = request.getSession();
		// 销毁session
		session.invalidate();
		// 到首页
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		
		return null;
	}

	
	
}
