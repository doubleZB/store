package cn.itcast.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.CateGory;
import cn.itcast.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<CateGory> findCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from category";
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class));
		return list;
	}

}
