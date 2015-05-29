package com.dazzilove.tobyspringstudy.user.service;

import com.dazzilove.tobyspringstudy.user.dao.UserDao;
import com.dazzilove.tobyspringstudy.user.domain.Level;
import com.dazzilove.tobyspringstudy.user.domain.User;

public class UserLevelUpgradePolicyImpl implements UserLevelUpgradePolicy {
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
		case BASIC : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER : return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
		case GOLD : return false;
		default : throw new IllegalArgumentException("Unkown Level : " + currentLevel); 
		}
	}

	public void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
}
