package cn.itcast.serviceimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cn.itcast.dao.OrderDao;
import cn.itcast.daoimpl.OrderDaoImpl;
import cn.itcast.domain.Order;
import cn.itcast.domain.Orderitem;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.OrderService;
import cn.itcast.utils.FactoryDemo;
import cn.itcast.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void save(Order order)
	{
			
		 	Connection con = null;
		try {
			// 创建dao
			OrderDao od=(OrderDao)FactoryDemo.getBean("OrderDao");
			// 开启事务
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			// 保存订单
			od.save(con,order);
			// 保存订单下的订单项
			List<Orderitem> itemList = order.getList();
			if(itemList.size()>0)
			{
				for (Orderitem item : itemList)
				{
					od.saveItem(con,item);
				}
			}
			// 提交事务
			DbUtils.commitAndClose(con);
		} catch (Exception e) {
			try {
				DbUtils.rollbackAndClose(con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public PageBean findOrder(User user, int pageNumber, int pageSize) throws Exception 
	{
		// 创建dao
		OrderDao od=(OrderDao)FactoryDemo.getBean("OrderDao");
		// 每页显示的数据
		List<Order> list = od.findOrder(user,pageNumber,pageSize);
		// 总条数
		int totalCount=od.count(user);
		// 总页数
		int totalPage=0;
		if(totalCount%pageSize==0)
		{
			totalPage=totalCount/pageSize;
		}else
		{
			totalPage=totalCount/pageSize+1;
		}
		
		PageBean pb = new PageBean();
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		pb.setTotalCount(totalCount);
		pb.setTotalPage(totalPage);
		pb.setList(list);
		
		return pb;
	}

	@Override
	public Order findByoid(String oid) throws Exception
	{
		// 创建dao
		OrderDao od=(OrderDao)FactoryDemo.getBean("OrderDao");
		
		return od.findByoid(oid);
	}

	@Override
	public void update(Order order) throws Exception {
		// 创建dao
		OrderDao od=(OrderDao)FactoryDemo.getBean("OrderDao");
		od.update(order);
	}

}
