package cn.itcast.daoimpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void regist(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		Object[] obj={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		qr.update(sql, obj);
		
	}

	@Override
	public User findByCode(String code) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from user where code=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		qr.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid());
	}

	@Override
	public User findUser(String username, String password) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from user where username=? and password=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}

}
