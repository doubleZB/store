package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;

public interface ProductService {

	PageBean findPage(String cid, int pageNumber, int pageSize) throws Exception;

	Product findBypid(String pid) throws SQLException;

	List<Product> findNew() throws SQLException;

}
