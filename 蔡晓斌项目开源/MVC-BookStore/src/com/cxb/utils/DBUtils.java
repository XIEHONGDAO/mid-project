package com.cxb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static ComboPooledDataSource pds = null;//数据库连接池
	static {
		if(pds==null) {
			System.out.println("数据库连接池初始化...");
			pds = new ComboPooledDataSource("c3p0");
		}
	}
	/**
	 * 从数据库连接池中获取数据库连接
	 * @return 数据库连接
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		return pds.getConnection();
	}
	/**
	 * 关闭数据库连接相关的资源（后用的先关）
	 * @param conn 数据库连接
	 * @param pstat 执行SQL的对象
	 * @param rs 存放查询结果的对象
	 */
	public static void closeResource(Connection conn,PreparedStatement pstat,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstat!=null) {
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
