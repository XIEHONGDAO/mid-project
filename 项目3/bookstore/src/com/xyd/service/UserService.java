package com.xyd.service;

import com.xyd.bean.UserInfo;
import com.xyd.daoimpl.UserInfoDaoImpl;

/**
 * userinfo  的业务层
 * @author Administrator
 *
 */
public class UserService {

	private UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
	
	
	/**
	 * 
	 * 检查 数据中是否 有这个 名字
	 * @return
	 */
	public int checkName(UserInfo user){
		
		return userInfoDaoImpl.checkName(user);
	}
	
	/**
	 * 添加 userinfo 就是注册操作
	 * @param userinfo
	 * @return
	 */
	public int addUserInfo(UserInfo userinfo){
		
		return userInfoDaoImpl.addUserInfo(userinfo);
	}

	/**
	 * 登录操作
	 * @return 
	 */
	public UserInfo login(UserInfo userInfo) {

		return userInfoDaoImpl.login(userInfo);
		
	}
}
