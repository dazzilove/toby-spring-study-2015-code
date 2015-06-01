package com.dazzilove.tobyspringstudy.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.dazzilove.tobyspringstudy.user.domain.User;

public class MockUserDao implements UserDao {
	private List<User> users;
	private List<User> updated = new ArrayList<User>();
	
	public MockUserDao() {
		
	}
	
	public MockUserDao(List<User> users) {
		this.users = users;
	}
	
	public List<User> getUpdated() {
		return this.updated;
	}

	public List<User> getAll() {
		return users;
	}

	public void update(User user) {
		updated.add(user);
	}

	public void add(User user) { throw new UnsupportedOperationException(); }
	public User get(String id) { throw new UnsupportedOperationException(); }
	public void deleteAll() { throw new UnsupportedOperationException(); }
	public int getCount() { throw new UnsupportedOperationException(); }

}
