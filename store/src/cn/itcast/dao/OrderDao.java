package cn.itcast.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.Order;
import cn.itcast.domain.Orderitem;
import cn.itcast.domain.User;

public interface OrderDao {

	void save(Connection con, Order order) throws SQLException;

	void saveItem(Connection con, Orderitem item) throws SQLException;

	List<Order> findOrder(User user, int pageNumber, int pageSize) throws SQLException, Exception;

	int count(User user) throws SQLException;

	Order findByoid(String oid) throws SQLException, Exception;

	void update(Order order) throws SQLException;

}
