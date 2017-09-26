package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.Product;

public interface ProductDao {

	List<Product> findPage(String cid, int pageNumber, int pageSize) throws SQLException;

	int count(String cid) throws SQLException;

	Product findBypid(String pid) throws SQLException;

	List<Product> findNew() throws SQLException;

	
}
