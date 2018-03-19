package com.cxb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cxb.dao.UserInfoDao;
import com.cxb.pojo.UserInfo;
import com.cxb.utils.DBUtils;

public class UserInfoDaoImpl implements UserInfoDao {
	private Connection conn = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	@Override
	public int addUser(UserInfo user) {
		try {
			//从数据库连接池中获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			String sql = "insert into bs_userinfo values(?,?,?)";
			//把Sql传入pstat中预编译
			pstat = conn.prepareStatement(sql);
			//填充占位符
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getEmail());
			//执行SQL语句
			int i = pstat.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, null);
		}
		return 0;
	}

	@Override
	public int updateUser(UserInfo user) {
		try {
			//从数据库连接池中获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			String sql = "update bs_userinfo set password=?,email=? where username=?";
			//把Sql传入pstat中预编译
			pstat = conn.prepareStatement(sql);
			//填充占位符
			pstat.setString(1, user.getPassword());
			pstat.setString(2, user.getEmail());
			pstat.setString(3, user.getUsername());
			//执行SQL语句
			int i = pstat.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, null);
		}
		return 0;
	}

	@Override
	public int delUserByUserName(String name) {
		try {
			//从数据库连接池中获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			String sql = "delete from bs_userinfo where username=?";
			//把Sql传入pstat中预编译
			pstat = conn.prepareStatement(sql);
			//填充占位符
			pstat.setString(1, name);
			//执行SQL语句
			int i = pstat.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, null);
		}
		return 0;
	}

	@Override
	public List<UserInfo> getAllUser() {
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			//从数据库连接池中获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			String sql = "select * from bs_userinfo";
			//把Sql传入pstat中预编译
			pstat = conn.prepareStatement(sql);
			//执行SQL语句,将结果放入resultset集合中
			rs = pstat.executeQuery();
			//遍历结果集
			while(rs.next()) {
				list.add(new UserInfo(rs.getString("username"), rs.getString("password"), rs.getString("email")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return list;
	}

	@Override
	public UserInfo getUserByUser(UserInfo user) {
		try {
			//从数据库连接池中获取数据库连接
			conn = DBUtils.getConn();
			//准备SQL语句
			StringBuffer sql = new StringBuffer("select * from bs_userinfo where 1=1 ");
			if(user.getUsername()!=null && !"".equals(user.getUsername())) {
				sql.append(" and username=?");
			}
			if(user.getPassword()!=null && !"".equals(user.getPassword())) {
				sql.append(" and password=?");
			}
			System.out.println(sql.toString());
			pstat = conn.prepareStatement(sql.toString());
			pstat.setString(1, user.getUsername());
			if(user.getPassword()!=null && !"".equals(user.getPassword())) {
				pstat.setString(2, user.getPassword());
			}
			rs = pstat.executeQuery();
			if(rs.next()) {
				return new UserInfo(rs.getString("username"), rs.getString("password"), rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, pstat, rs);
		}
		return null;
	}

}

