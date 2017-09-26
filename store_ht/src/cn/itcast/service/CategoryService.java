package cn.itcast.service;

import java.sql.SQLException;

import cn.itcast.domain.CateGory;
import cn.itcast.domain.Product;

public interface CategoryService {

	String findCategory(int pageNumber, int pageSize) throws Exception;

	void delBycid(String cid) throws SQLException, Exception;

	void save(CateGory cg) throws Exception;

	void update(CateGory cg) throws Exception;

	void saveProduct(Product p) throws Exception;

}
