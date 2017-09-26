package cn.itcast.service;

import cn.itcast.domain.Order;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

public interface OrderService {

	void save(Order order);

	PageBean findOrder(User user, int pageNumber, int pageSize) throws Exception;

	Order findByoid(String oid) throws Exception;

	void update(Order order) throws Exception;

}
