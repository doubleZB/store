package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.CateGory;

public interface CategoryDao {

	List<CateGory> findCategory() throws SQLException;

}
