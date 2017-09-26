package cn.itcast.service;

import java.sql.SQLException;

import cn.itcast.domain.User;

public interface UserService 
{

	void regist(User user) throws SQLException;

	User findByCode(String code) throws SQLException;

	void update(User user) throws SQLException;

	User findUser(String username, String password) throws SQLException;

}
