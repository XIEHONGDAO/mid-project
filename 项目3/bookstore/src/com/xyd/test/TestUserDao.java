package com.xyd.test;

import org.junit.Before;
import org.junit.Test;

import com.xyd.bean.UserInfo;
import com.xyd.daoimpl.UserInfoDaoImpl;
import com.xyd.utils.MD5Utils;

/**
 * 用户 测试类
 * @author Administrator
 *
 */
public class TestUserDao {

	
	UserInfoDaoImpl userDao = null;
	
	//测试 每次之前都会调用 （可以初始化 一些数据）
	@Before
	public void getUserDao(){
		
		if (userDao == null) {
			userDao = new UserInfoDaoImpl();
		}
		
	}
	
	@Test
	public void checName(){
		
		System.out.println(userDao.checkName(new UserInfo("test", null, null)));
		
	}
	
	@Test
	public void addUser(){
		
		System.out.println(userDao.addUserInfo(new UserInfo("aa","aa", "123@qq.com")));
		
	}
	
}
