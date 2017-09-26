package cn.itcast.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findPage(String cid, int pageNumber, int pageSize) throws SQLException {
		// 求起始数据
		int start=(pageNumber-1)*pageSize;
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),cid,start,pageSize);
		return list;
	}

	@Override
	public int count(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from product where cid=?";
		Long l =(Long)qr.query(sql, new ScalarHandler(),cid);
		return l.intValue();
	}

	@Override
	public Product findBypid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from product where pid=?";
		Product p = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return p;
	}

	@Override
	public List<Product> findNew() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from product where pflag=0 ORDER BY pdate DESC LIMIT 0,9";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}



}
