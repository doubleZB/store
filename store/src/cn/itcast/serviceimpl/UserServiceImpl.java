package cn.itcast.serviceimpl;

import java.sql.SQLException;

import cn.itcast.dao.UserDao;
import cn.itcast.daoimpl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) throws SQLException {
		// 创建dao
		UserDao ud=new UserDaoImpl();
		ud.regist(user);
		
	}

	@Override
	public User findByCode(String code) throws SQLException {
		// 创建dao
		UserDao ud=new UserDaoImpl();
		return ud.findByCode(code);
		
	}

	@Override
	public void update(User user) throws SQLException {
		// 创建dao
		UserDao ud=new UserDaoImpl();
		ud.update(user);
	}

	@Override
	public User findUser(String username, String password) throws SQLException {
		// 创建dao
		UserDao ud=new UserDaoImpl();
		return ud.findUser(username,password);
		
	}

}
