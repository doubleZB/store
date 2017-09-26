package cn.itcast.daoimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.CateGory;
import cn.itcast.domain.Product;
import cn.itcast.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Map<String, Object>> findProduct(int pageNumber,int pageSize) throws SQLException 
	{
		int start=(pageNumber-1)*pageSize;
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from product p,category c where p.cid=c.cid limit ?,?";
		List<Map<String, Object>> mList = qr.query(sql, new MapListHandler(),start,pageSize);
		return mList;
	}

	@Override
	public int Count() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from product";
		Long l =(Long)qr.query(sql, new ScalarHandler());
		return l.intValue();
		 
	}

	@Override
	public List<CateGory> findCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from category";
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class));
		return list;
	}

}
