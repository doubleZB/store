package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.CateGory;
import cn.itcast.domain.Product;

public interface CategoryDao {

	List<CateGory> findCategory(int pageNumber, int pageSize) throws SQLException;

	int Count() throws SQLException;

	void delBycid(String cid) throws SQLException;

	void save(CateGory cg) throws SQLException;

	void update(CateGory cg) throws SQLException;

	void saveProduct(Product p) throws SQLException;

}
