package com.dazzilove.tobyspringstudy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.dazzilove.learningtest.proxytest.SimpleProxyTest;
import com.dazzilove.tobyspringstudy.user.dao.UserDaoSpringTest;
import com.dazzilove.tobyspringstudy.user.domain.UserTest;
import com.dazzilove.tobyspringstudy.user.service.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ UserDaoSpringTest.class, 
		UserTest.class, 
		UserServiceTest.class,
		SimpleProxyTest.class })
public class AllTests {

}
