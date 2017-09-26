package cn.itcast.service;

import java.sql.SQLException;

public interface ProductService {

	String findProduct(int pageNumber,int pageSize) throws Exception;

	String findCategory() throws SQLException;

}
