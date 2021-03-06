package com.dazzilove.learningtest.proxytest;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.dazzilove.learningtest.proxytest.Hello;
import com.dazzilove.learningtest.proxytest.HelloTarget;
import com.dazzilove.learningtest.proxytest.HellowUppercase;

public class SimpleProxyTest {

	@Test
	public void helloTest() {
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"), is("Thank You Toby"));
	}
	
	@Test
	public void helloProxyTest() {
		Hello proxiedHello = new HellowUppercase(new HelloTarget());
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
	}

}
