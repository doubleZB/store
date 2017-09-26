package cn.itcast.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.CateGory;
import cn.itcast.domain.Product;
import cn.itcast.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<CateGory> findCategory(int pageNumber, int pageSize) throws SQLException
	{
		int start=(pageNumber-1)*pageSize;
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql="select * from category limit ?,?";
		
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class),start,pageSize);
		
		return list;
	}

	@Override
	public int Count() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from category";
		Long l =(Long)qr.query(sql, new ScalarHandler());
		return l.intValue();
	}

	@Override
	public void delBycid(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from category where cid=?";
		qr.update(sql,cid);
		
	}

	@Override
	public void save(CateGory cg) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into category values(?,?)";
		qr.update(sql,cg.getCid(),cg.getCname());
		
	}

	@Override
	public void update(CateGory cg) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="update category set cname=? where cid=?";
		qr.update(sql,cg.getCname(),cg.getCid());
		
	}

	@Override
	public void saveProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object[] obj={p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCateGory().getCid()};
		qr.update(sql, obj);
	}

}
