package com.dazzilove.tobyspringstudy.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dazzilove.tobyspringstudy.user.domain.User;

public class CountingDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDaoJdbc userDao = context.getBean("userDao", UserDaoJdbc.class);
		
		User user = new User();
		user.setId("dazzilove");
		user.setName("류성희");
		user.setPassword("qawsedrf");
		
		userDao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공");

		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println(ccm.getCounter());
	}
}
