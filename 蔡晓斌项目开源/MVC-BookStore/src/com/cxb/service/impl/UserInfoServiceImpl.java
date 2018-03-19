package com.cxb.service.impl;

import java.util.List;

import com.cxb.dao.UserInfoDao;
import com.cxb.dao.impl.UserInfoDaoImpl;
import com.cxb.pojo.UserInfo;
import com.cxb.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDao userDao = new UserInfoDaoImpl();
	@Override
	public int addUser(UserInfo user) {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(UserInfo user) {
		return userDao.updateUser(user);
	}

	@Override
	public int delUserByUserName(String name) {
		return userDao.delUserByUserName(name);
	}

	@Override
	public List<UserInfo> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public UserInfo getUserByUser(UserInfo user) {
		if(user.getUsername()==null || "".equals(user.getUsername())) {
			return null;
		}
		return userDao.getUserByUser(user);
	}

}
