package com.xyd.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * jdbc 的连接工具类
 * @author Administrator
 *
 */
public class JdbcUtils {

	static ComboPooledDataSource combo = new ComboPooledDataSource("oracle");
	
	/**
	 * 获取数据库的 connection
	 * 
	 * @return  conn
	 */
	public static Connection getConn(){
		try {
			return combo.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 数据资源释放  放到 连接池  （不是关闭）
	 * 
	 * @param auto
	 */
	public static void coloseAll(AutoCloseable ... auto){
		
		for (AutoCloseable autoCloseable : auto) {
			
			if (autoCloseable != null) {
				
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
