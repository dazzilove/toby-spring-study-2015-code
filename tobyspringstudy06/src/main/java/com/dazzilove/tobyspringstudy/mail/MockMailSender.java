package com.dazzilove.tobyspringstudy.mail;

import java.util.ArrayList;
import java.util.List;

public class MockMailSender implements MailSender {
	private List<String> requests = new ArrayList<String>();
	String host;
	
	public List<String> getRequests() {
		return this.requests;
	}
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void send(SimpleMailMessage simpleMailMessage) throws MailException {
		requests.add(simpleMailMessage.getTo());
	}

	public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {
		// TODO Auto-generated method stub
	}

}
