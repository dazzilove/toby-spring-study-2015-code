package com.dazzilove.tobyspringstudy.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.dazzilove.tobyspringstudy.user.domain.User;

public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	void update(User user);
}
