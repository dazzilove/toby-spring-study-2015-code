package com.dazzilove.tobyspringstudy.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dazzilove.tobyspringstudy.user.domain.User;

public interface UserDao {
	public void add(User user);
	public User get(String id);
	public List<User> getAll();
	public void deleteAll();
	public int getCount();
	public void update(User user);
}
