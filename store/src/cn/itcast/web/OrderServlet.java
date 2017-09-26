package cn.itcast.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Order;
import cn.itcast.domain.Orderitem;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.OrderService;
import cn.itcast.serviceimpl.OrderServiceImpl;
import cn.itcast.utils.BaseServlet;
import cn.itcast.utils.FactoryDemo;
import cn.itcast.utils.PaymentUtil;
import cn.itcast.utils.UUIDUtils;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;

	public String addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			// 先获取购物车的数据用来封装订单和订单下的商品
			HttpSession session = request.getSession();
			Cart cart =(Cart)session.getAttribute("cart");
			// 封装订单
			Order order = new Order();
			// 封装订单编号
			order.setOid(UUIDUtils.getUUID());
			// 封装下单时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(new Date());
			order.setOrdertime(date);
			// 封装订单的总金额
			order.setTotal(cart.getTotal());
			// 封装订单状态 //未付款
			order.setState(0);
			// 订单属于哪个用户
			User user =(User)session.getAttribute("user");
			if(user==null)
			{
				// 请登录再操作下单
				request.setAttribute("msg", "亲,你当前的操作需要先登录才能进行");
				return "/jsp/info.jsp";
			}
			order.setUser(user);
			
			// 获取该订单的订单项集合
			List<Orderitem> itemList = order.getList();
			
			// 封装订单下的订单项商品
			Map<String, CartItem> map = cart.getMap();
			for(String key:map.keySet())
			{
				// 有一个购物项==一个订单项
				Orderitem item = new Orderitem();
				item.setItemid(UUIDUtils.getUUID());
				item.setCount(map.get(key).getCount());
				item.setProduct(map.get(key).getProduct());
				item.setSubtotal(map.get(key).getSubtotal());
				// 当前订单项属于哪个订单
				item.setOrder(order);	
				// 把每一个封装好的orderitem对象放在订单的订单项集合下
				itemList.add(item);
			}
			// 存订单--order
			OrderService os =(OrderService)FactoryDemo.getBean("OrderService");
			os.save(order);
			
			// 清空购物车
			cart.removeAll();
			
			// 带着order的所有数据到页面展示 order_info.jsp
			request.setAttribute("order", order);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return "/jsp/order_info.jsp";
	}
	
	// 我的订单
	public String myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			// 获取当前页
			int pageNumber =Integer.parseInt(request.getParameter("pageNumber"));
			// 设定每页的条数
			int pageSize=3;
			// 获取当前用户
			HttpSession session = request.getSession();
			User user =(User)session.getAttribute("user");
			// session30分钟不使用自动过期
			if(user==null)
			{
				request.setAttribute("msg", "亲,请重新登录吧..");
				return "/jsp/info.jsp";
			}
			// 调用service
			OrderService os =(OrderService)FactoryDemo.getBean("OrderService");
			PageBean pb = os.findOrder(user,pageNumber,pageSize);
			request.setAttribute("pb", pb);
			
		} catch (Exception e) {
			request.setAttribute("msg", "查询订单失败,请等待继续下次查询");
			return "/jsp/info.jsp";
		}
		
		
		
		return "/jsp/order_list.jsp";
	}
	
	public String findByoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			// 获取要付款的订单号
			String oid = request.getParameter("oid");
			// 调用service
			OrderService os =(OrderService)FactoryDemo.getBean("OrderService");
			
			Order order = os.findByoid(oid);
			
			request.setAttribute("order", order);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/jsp/order_info.jsp";
	}
	
	
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try {
			
			String oid = request.getParameter("oid");
			// 根据oid获取订单对象
			OrderService os =(OrderService)FactoryDemo.getBean("OrderService");
			
			Order order = os.findByoid(oid);
			String address = request.getParameter("address");
			String name = request.getParameter("name");
			String telephone= request.getParameter("telephone");
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			
			// 先修改
			os.update(order);
		
			
			// 去调用第三方支付平台的接口,还需要传人家要的参数
			String p0_Cmd = "Buy";
			String p1_MerId = "10001126856";
			String p2_Order = order.getOid();
			String p3_Amt = "0.01";//测试用1分钱，真正开发中用order.getTotal();
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			String p8_Url = "http://localhost:8080"+request.getContextPath()+"/order?method=callBack";
			String p9_SAF = "0";
			String pa_MP = "";
			String pd_FrpId = request.getParameter("pd_FrpId");
			String pr_NeedResponse = "1";
			// 电子签名
			String hmac =PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			
			StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			buffer.append("p0_Cmd="+p0_Cmd);
			buffer.append("&p1_MerId="+p1_MerId);
			buffer.append("&p2_Order="+p2_Order);
			buffer.append("&p3_Amt="+p3_Amt);
			buffer.append("&p4_Cur="+p4_Cur);
			buffer.append("&p5_Pid="+p5_Pid);
			buffer.append("&p6_Pcat="+p6_Pcat);
			buffer.append("&p7_Pdesc="+p7_Pdesc);
			buffer.append("&p8_Url="+p8_Url);
			buffer.append("&p9_SAF="+p9_SAF);
			buffer.append("&pa_MP="+pa_MP);
			buffer.append("&pd_FrpId="+pd_FrpId);
			buffer.append("&pr_NeedResponse="+pr_NeedResponse);
			buffer.append("&hmac="+hmac);
			response.sendRedirect(buffer.toString());
				  

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			// 修改订单状态为1
			String oid = request.getParameter("r6_Order");
			OrderService os =(OrderService)FactoryDemo.getBean("OrderService");
			Order order = os.findByoid(oid);
			order.setState(1);
			os.update(order);
			
			// 告诉浏览器支付成功
			request.setAttribute("msg", "支付成功,你为这个订单"+oid+"支付了"+request.getParameter("r3_Amt")+"元钱");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/jsp/info.jsp";
		
	}
	
	
}
