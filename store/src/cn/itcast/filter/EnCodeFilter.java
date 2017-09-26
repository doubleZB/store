package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.utils.GZrequest;

public class EnCodeFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 1 先获取提交方式
		HttpServletRequest req=(HttpServletRequest)request;
		String method = req.getMethod();
		
		// 2 判断
		if("post".equalsIgnoreCase(method))
		{
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, response);
		}else if("get".equalsIgnoreCase(method))
		{
			// 创建一个装饰类(增强类)
			GZrequest gZrequest = new GZrequest(req);
			//放行
			chain.doFilter(gZrequest, response);
		}else
		{
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
