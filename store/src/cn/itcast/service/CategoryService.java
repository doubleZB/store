package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.domain.CateGory;

public interface CategoryService {

	String findCateogry() throws SQLException;

	String findCateogryByRedis() throws SQLException;

}
