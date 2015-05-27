package com.dazzilove.tobyspringstudy.mail;

public interface MailSender {
	void send(SimpleMailMessage simpleMailMessage) throws MailException;
	void send(SimpleMailMessage[] simpleMailMessages) throws MailException;
}
