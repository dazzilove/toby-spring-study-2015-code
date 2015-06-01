package com.dazzilove.tobyspringstudy.user.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dazzilove.tobyspringstudy.mail.MailSender;
import com.dazzilove.tobyspringstudy.mail.SimpleMailMessage;
import com.dazzilove.tobyspringstudy.user.dao.UserDao;
import com.dazzilove.tobyspringstudy.user.domain.Level;
import com.dazzilove.tobyspringstudy.user.domain.User;

public class UserServiceImpl implements UserService {
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	
	private UserDao userDao;
	private MailSender mailSender;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for(User user : users) {
			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	public void add(User user) {
		if (user.getLevel() == null) user.setLevel(Level.BASIC); 
		this.userDao.add(user);
	}
	
	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
		case BASIC : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER : return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
		case GOLD : return false;
		default : throw new IllegalArgumentException("Unkown Level : " + currentLevel); 
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEmail(user);
	}
	
	private void sendUpgradeEmail(User user) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("userAdmin@ksug.org");
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevel().name() +"로 업그레이드 되었습니다.");
		
		this.mailSender.send(mailMessage);
//		
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "mail.ksug.org");
//		Session s = Session.getInstance(props, null);
//		
//		MimeMessage message = new MimeMessage(s);
//		try {
//			message.setFrom(new InternetAddress("userAdmin@ksug.org"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//			message.setSubject("Upgrade 안내");
//			message.setText("사용자님의 등급이 " + user.getLevel().name() +"로 업그레이드 되었습니다.");
//			Transport.send(message);
//		} catch(AddressException e) {
//			throw new RuntimeException(e);			
//		} catch(MessagingException e) {
//			throw new RuntimeException(e);	
//		}
	}
}
