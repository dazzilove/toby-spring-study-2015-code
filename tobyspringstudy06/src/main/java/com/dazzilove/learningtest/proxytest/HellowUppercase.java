package com.dazzilove.learningtest.proxytest;

public class HellowUppercase implements Hello {
	
	Hello hello;
	
	public HellowUppercase(Hello hello) {
		this.hello = hello;
	}

	public String sayHello(String name) {
		return hello.sayHello(name).toUpperCase();
	}

	public String sayHi(String name) {
		return hello.sayHi(name).toUpperCase();
	}

	public String sayThankYou(String name) {
		return hello.sayThankYou(name).toUpperCase();
	}

}
