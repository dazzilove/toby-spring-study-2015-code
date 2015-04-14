package com.dazzilove.tobyspringstudy.user.domain;

/**
 * create table users {
 * 	id varchar(10) primary key,
 * 	name varchar(20) not null,
 * 	password varchar(10) not null
 * }
 */

public class User {
	String id;
	String name;
	String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}