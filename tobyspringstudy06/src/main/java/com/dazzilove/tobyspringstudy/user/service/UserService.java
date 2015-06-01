package com.dazzilove.tobyspringstudy.user.service;

import com.dazzilove.tobyspringstudy.user.domain.User;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}
