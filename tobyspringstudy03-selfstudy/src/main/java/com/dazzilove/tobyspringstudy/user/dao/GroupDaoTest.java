package com.dazzilove.tobyspringstudy.user.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="applicationContext.xml")
public class GroupDaoTest {
	
	@Autowired
	private ApplicationContext context;
	
	UserDao dao;
	
	@Test
	public void test() {
		dao = this.context.getBean("userDao", UserDao.class);
	}

}
