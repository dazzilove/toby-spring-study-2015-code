package com.dazzilove.tobyspringstudy.user.domain;

/**
 * create table users (
 * 	id varchar(10) primary key,
 * 	name varchar(20) not null,
 * 	password varchar(10) not null
 * );
 * 
 * alter table users add (level tinyint not null);
 * alter table users add (login int not null);
 * alter table users add (recommend int not null);
 */

public class User {
	String id;
	String name;
	String password;
	String email;
	
	Level level;
	int login;
	int recommend;
	
	public User() {
		
	}
	
	public User(String id, String name, String password, Level level, int login, int recommend, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
		this.email = email;
	}
	
	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public void upgradeLevel() {
		Level nextLevel = this.level.nextLevel();
		if (nextLevel == null) {
			throw new IllegalArgumentException(this.level + "은 업그레이드가 불가능합니다.");
		} else {
			this.level = nextLevel;
		}
	}
	
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getEmail() {
		return "dazzilove@11st.co.kr";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}