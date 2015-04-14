package com.dazzilove.tobyspringstudy.tobyspringstudy01.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(this.connectionMaker());
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
		return new DConnectionMaker();
	}
}
