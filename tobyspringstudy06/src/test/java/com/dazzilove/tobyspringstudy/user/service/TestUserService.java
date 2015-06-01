package com.dazzilove.tobyspringstudy.user.service;

import com.dazzilove.tobyspringstudy.user.domain.User;

public class TestUserService extends UserServiceImpl {
	private String id;
	
	TestUserService(String id) {
		this.id = id;
	}
	
	protected void upgradeLevel(User user) {
		if (user.getId().equals(this.id)) throw new TestUserServiceException();
		super.upgradeLevel(user);
	}

	static class TestUserServiceException extends RuntimeException {
		// Nothing TODO
	}
	
}



