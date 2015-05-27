package com.dazzilove.tobyspringstudy.user.service;

import com.dazzilove.tobyspringstudy.user.domain.User;

public interface UserLevelUpgradePolicy {
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
}
