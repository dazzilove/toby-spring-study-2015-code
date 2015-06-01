package com.dazzilove.tobyspringstudy.user.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dazzilove.tobyspringstudy.user.domain.User;

public class UserServiceTx implements UserService {
	
	UserService userService;
	PlatformTransactionManager transactionManager;
	
	
	public void setUserService(UserService userSerive) {
		this.userService = userSerive;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void add(User user) {
		userService.add(user);
	}

	public void upgradeLevels() {
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}

}
