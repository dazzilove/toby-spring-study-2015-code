package com.dazzilove.learningtest.spring.factorybean;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	// 설정파일 이름을 지정하지 않으면 클래스이름+"-context.xml"이 디폴트로 사용된다.
public class FactoryBeanTest {
	
	@Autowired
	ApplicationContext context;

	@Test
	public void getMessageFromFactoryBean1() {
		Object message = context.getBean("message");
		assertThat((Message)message, is(Message.class));	
		assertThat(((Message)message).getText(), is("Factory Bean"));
	}
	
	@Test
	public void getFactoryBean() throws Exception {
		Object factory = context.getBean("&message");
		assertThat((MessageFactoryBean)factory, is(MessageFactoryBean.class));
	}	
	
}




