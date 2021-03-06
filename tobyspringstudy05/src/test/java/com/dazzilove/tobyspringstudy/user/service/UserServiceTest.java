package com.dazzilove.tobyspringstudy.user.service;

import static com.dazzilove.tobyspringstudy.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static com.dazzilove.tobyspringstudy.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.dazzilove.tobyspringstudy.mail.MailSender;
import com.dazzilove.tobyspringstudy.mail.MockMailSender;
import com.dazzilove.tobyspringstudy.user.dao.UserDao;
import com.dazzilove.tobyspringstudy.user.domain.Level;
import com.dazzilove.tobyspringstudy.user.domain.User;
import com.dazzilove.tobyspringstudy.user.service.TestUserService.TestUserServiceException;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="../test-applicationContext.xml")
public class UserServiceTest {
	
	List<User> users;
	
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	DataSource dataSource;
	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	MailSender mailSender;
	
	@Before
	public void setUp() {
		users = Arrays.asList(
					new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0, "bumjin@11st.co.kr"),
					new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@11st.co.kr"),
					new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1, "erwins@11st.co.kr"),
					new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "madnite1@11st.co.kr"),
					new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@11st.co.kr")
				);
	}
	
	@Test
	@Ignore
	public void updateLevel() throws Exception {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
	}
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if(upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	
	}
	
	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserService testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(userDao);
		testUserService.setTransactionManager(transactionManager);
		testUserService.setMailSender(mailSender);
		
		userDao.deleteAll();
		for(User user:users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		} catch(TestUserServiceException e) {
			// Nothing TODO
		} 
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		
		for(User user:users) userDao.add(user);
		
		MockMailSender mockMailSender = new MockMailSender();
		userService.setMailSender(mockMailSender);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
		List<String> request = mockMailSender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));
	}


//	@Test
//	public void bean() {
//		assertThat(this.userService, is(notNullValue()));
//		
//	}
	
}