package com.xyd.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xyd.bean.UserInfo;
import com.xyd.dao.UserInfoDao;
import com.xyd.utils.JdbcUtils;

/***
 * 
 * userinfo 持久dao 的接口实现类
 * @author Administrator
 *
 */
public class UserInfoDaoImpl implements UserInfoDao{

	// 数据连接 接口
	private Connection conn = null;
	//数据预编译 操作 接口
	private PreparedStatement ps = null;
	//数据操作 返回接口集
	private ResultSet rs = null;
	
	@Override
	public int checkName(UserInfo user) {
		
		//获取conn
		conn = JdbcUtils.getConn();
		
		//获取预 接口
		try {
			ps = conn.prepareStatement("select * from bs_userinfo where username = ?");
			
			//组装数据
			ps.setString(1, user.getUsername());
			
			//操作
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return 1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtils.coloseAll(rs,ps,conn);
		}
		return 0;
	}

	/**
	 * 添加 user的操作 
	 *  注册  如果返回值为int  就是注册 成功
	 */
	@Override
	public int addUserInfo(UserInfo user) {
		
		conn = JdbcUtils.getConn();
		
		try {
			ps = conn.prepareStatement("insert into bs_userinfo(username,password,email) values (?,?,?)");
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			
			int executeUpdate = ps.executeUpdate();
			return executeUpdate;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//释放资源
			JdbcUtils.coloseAll(rs,ps,conn);
		}
		
		return 0;
	}

	/**
	 * 登录操作
	 */
	public UserInfo login(UserInfo userInfo) {
		//获取conn
				conn = JdbcUtils.getConn();
				
				//获取预 接口
				try {
					ps = conn.prepareStatement("select * from bs_userinfo where username = ? and password = ? ");
					
					//组装数据
					ps.setString(1, userInfo.getUsername());
					ps.setString(2, userInfo.getPassword());
					
					//操作
					rs = ps.executeQuery();
					
					if (rs.next()) {
						return new UserInfo(rs.getString("username"), 
								rs.getString("password"), rs.getString("email"));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					//释放资源
					JdbcUtils.coloseAll(rs,ps,conn);
				}
				return null;
	}

}
