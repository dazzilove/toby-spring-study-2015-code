package com.dazzilove.tobyspringstudy.tobyspringstudy01.user.dao;

import org.springframework.context.annotation.Bean;

public class CountingDaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}

	@Bean
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}

	@Bean
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
}
