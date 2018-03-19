package com.xyd.dao;

import java.util.List;

import com.xyd.bean.Book;

/**
 * 持久化 接口
 * @author Administrator
 *
 */
public interface BookDao {


	/**
	 * 获取Book数据  
	 * @return
	 */
	List<Book> getBook(int max,int min);
	
	/**
	 * 查询书籍 的数据
	 * @param bname
	 * @return
	 */
	int getBookCount();

	/**
	 * 模糊查询数量
	 * @param keyword
	 * @return
	 */
	int getBookCount(String keyword);

	/**
	 * 模糊查询
	 * @param max
	 * @param min
	 * @param keyword
	 * @return
	 */
	List<Book> getBook(int max, int min, String keyword);
}
