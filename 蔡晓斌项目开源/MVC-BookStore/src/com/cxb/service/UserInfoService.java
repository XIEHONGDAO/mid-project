package com.cxb.service;

import java.util.List;

import com.cxb.pojo.UserInfo;

public interface UserInfoService {
	/**
	 * 添加用户（注册）
	 * @param user 用户信息对象
	 * @return 返回添加是否成功，0表示失败
	 */
	int addUser(UserInfo user);
	/**
	 * 修改用户信息
	 * @param user 新的用户信息
	 * @return 修改是否成功
	 */
	int updateUser(UserInfo user);
	/**
	 * 根据用户主键（用户名）删除用户信息
	 * @param name 用户主键
	 * @return 删除是否成功
	 */
	int delUserByUserName(String name);
	/**
	 * 查询所有用户信息
	 * @return 所有用户集合
	 */
	List<UserInfo> getAllUser();
	/**
	 * 查询用户信息根据用户信息查询
	 * @param user 用户信息
	 * @return 用户信息
	 */
	UserInfo getUserByUser(UserInfo user);
}
