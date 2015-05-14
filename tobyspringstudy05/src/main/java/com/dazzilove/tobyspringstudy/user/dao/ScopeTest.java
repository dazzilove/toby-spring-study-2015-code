package com.dazzilove.tobyspringstudy.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {
	
	public static void main(String[] arg) {
		DaoFactory factory = new DaoFactory();
		UserDaoJdbc dao1 = factory.userDao();
		UserDaoJdbc dao2 = factory.userDao();
		
		System.out.println(dao1);
		System.out.println(dao2);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDaoJdbc dao3 = context.getBean("userDao", UserDaoJdbc.class);
		UserDaoJdbc dao4 = context.getBean("userDao", UserDaoJdbc.class);
		
		System.out.println(dao3);
		System.out.println(dao4);
	}

}
