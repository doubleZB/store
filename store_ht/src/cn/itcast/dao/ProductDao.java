package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.itcast.domain.CateGory;

public interface ProductDao {

	List<Map<String, Object>> findProduct(int pageNumber,int pageSize) throws SQLException;

	int Count() throws SQLException;

	List<CateGory> findCategory() throws SQLException;

}
