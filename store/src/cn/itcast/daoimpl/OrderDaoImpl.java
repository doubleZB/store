package cn.itcast.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Order;
import cn.itcast.domain.Orderitem;
import cn.itcast.domain.Product;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void save(Connection con, Order order) throws SQLException
	{
		QueryRunner qr = new QueryRunner();
		String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] obj={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		qr.update(con,sql,obj);
	}

	@Override
	public void saveItem(Connection con, Orderitem item) throws SQLException 
	{
		QueryRunner qr = new QueryRunner();
		String sql="insert into orderitem values(?,?,?,?,?)";
		Object[] obj={item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		qr.update(con, sql,obj);
		
	}

	@Override
	public List<Order> findOrder(User user, int pageNumber, int pageSize) throws Exception
	{
		int start=(pageNumber-1)*pageSize;
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from orders where uid=? limit ?,?";
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),start,pageSize);
		
		// 遍历每一个订单
		if(list!=null)
		{
			for (Order order: list) 
			{
				// 获取每一个订单的订单项集合
				List<Orderitem> itemList = order.getList();
				// 根据每一个订单,查到这个订单下的所有订单项
				String sql2="select * from orderitem o,product p where o.oid=? and o.pid=p.pid";
				List<Map<String, Object>> mList = qr.query(sql2,new MapListHandler(),order.getOid());
				// 遍历循环每一个订单项的数据
				for (Map<String, Object> map : mList) 
				{
					// 拿每一个订单项的数据进行封装====Orderitem
					Orderitem item = new Orderitem();
					BeanUtils.populate(item,map);
					Product product = new Product();
					BeanUtils.populate(product, map);
					// 把商品对象反倒item
					item.setProduct(product);
					
					// 把封装好的每一个订单项数据放到订单下的订单项集合中
					itemList.add(item);
					
				}
			}
		}
		
		return list;
		
	}

	@Override
	public int count(User user) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from orders where uid=?";
		Long l =(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return l.intValue();
	}

	@Override
	public Order findByoid(String oid) throws Exception 
	{
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from orders where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		// 要获取该订单下的所有订单项
		if(order!=null)
		{
			List<Orderitem> itemList = order.getList();
			
			String sql2="select * from orderitem o,product p where o.oid=? and o.pid=p.pid";
			List<Map<String, Object>> mList = qr.query(sql2, new MapListHandler(),order.getOid());
			
			for (Map<String, Object> map : mList) 
			{
					// 创建订单项
				Orderitem item = new Orderitem();
				BeanUtils.populate(item, map);
				Product product = new Product();
				BeanUtils.populate(product, map);
				item.setProduct(product);
				
				itemList.add(item);
			}
		}
		
		return order;
	}

	@Override
	public void update(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="update orders set state=?,address=?,NAME=?,telephone=? where oid=?";
		qr.update(sql,order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	

}
