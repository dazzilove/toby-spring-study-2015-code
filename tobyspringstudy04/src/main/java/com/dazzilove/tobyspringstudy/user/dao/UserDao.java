package com.dazzilove.tobyspringstudy.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.dazzilove.tobyspringstudy.user.domain.User;

public interface UserDao {
	void add(User user)  throws ClassNotFoundException, SQLException;
	User get(String id) throws ClassNotFoundException, SQLException;
	List<User> getAll();
	void deleteAll() throws SQLException;
	int getCount() throws SQLException;
}
