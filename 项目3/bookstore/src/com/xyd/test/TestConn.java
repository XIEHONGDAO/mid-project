package com.xyd.test;

import org.junit.Test;

import com.xyd.utils.JdbcUtils;

/**
 * 测试  数据库的 conn
 * @author Administrator
 *
 */
public class TestConn {

	@Test
	public void testConn(){
		System.out.println(JdbcUtils.getConn());
		
	}
	
}
