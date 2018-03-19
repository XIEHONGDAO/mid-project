package com.xyd.dao;

import com.xyd.bean.UserInfo;

/**
 * UserInfo的dao
 * @author Administrator
 *
 */
public interface UserInfoDao {

	/**
	 * 检查 用户名是否已将存在
	 * @param user
	 * @return  1 就是存在    0 就是不存在
	 * 
	 */
	int checkName(UserInfo user);
	
	/**
	 * 注册 
	 * @param user
	 * @return  1 就是注册成功
 	 */
	int addUserInfo(UserInfo user);
	
}
