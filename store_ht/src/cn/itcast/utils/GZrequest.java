package cn.itcast.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GZrequest extends HttpServletRequestWrapper
{
	// 要被装饰的对象
	private HttpServletRequest request;

	public GZrequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}

	// 增强原本request对象的getParameter
	@Override
	public String getParameter(String name) {
		// 乱码
		String value = request.getParameter(name);
		System.out.println(value);
		//增强
		try {
			value = new String(value.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}
