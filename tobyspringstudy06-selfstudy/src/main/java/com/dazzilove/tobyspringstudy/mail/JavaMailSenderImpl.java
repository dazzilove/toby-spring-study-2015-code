package com.dazzilove.tobyspringstudy.mail;


public class JavaMailSenderImpl implements MailSender {

	String host;

	public void setHost(String host) {
		this.host = host;
	}
	
	public void send(SimpleMailMessage simpleMailMessage) throws MailException {
		// TODO Auto-generated method stub
	}

	public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {
		// TODO Auto-generated method stub
	}

}
