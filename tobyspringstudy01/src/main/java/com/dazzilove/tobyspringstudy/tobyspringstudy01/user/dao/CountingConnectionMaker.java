package com.dazzilove.tobyspringstudy.tobyspringstudy01.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class CountingConnectionMaker implements ConnectionMaker {
	int counter = 0;
	ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker connectionMaker) {
		this.realConnectionMaker = connectionMaker;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return this.realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}

}
