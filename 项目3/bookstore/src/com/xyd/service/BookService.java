package com.xyd.service;

import java.util.List;

import com.xyd.bean.Book;
import com.xyd.dao.BookDao;
import com.xyd.daoimpl.BookDaoImpl;

/**
 * 
 * @author Administrator
 *
 */
public class BookService {

	private BookDao bookDao = new BookDaoImpl(); 
	/**
	 * 查询书籍的操作 
	 * @param page     当前的页数 
	 * @param pageSize 每页显示的条目
	 * @return
	 */
	public List<Book> getBooks(int page,int pageSize){
		
		// 2     4
		// p1 = 1,2,3,4     p2 = 5,6,7,8
		// 2   3 
		// p1 = 1,2,3  p2 = 4,5,6
		int max = page * pageSize;
		int min = (page -1) * pageSize +1; 
		return bookDao.getBook(max, min);
		
	}
	
	/**
	 * 拿到 书本的数量
	 * @return
	 */
	public int getBookCount() {
		
		return bookDao.getBookCount();
	}

	/**
	 * 模糊查询  书籍
	 * @param index
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	public List<Book> getBooks(int page, int pageSize, String keyword) {
		
		int max = page * pageSize;
		int min = (page -1) * pageSize +1; 
		return bookDao.getBook(max, min,keyword);
	}

	/**
	 * 模糊查询数量
	 * @param keyword
	 * @return
	 */
	public int getBookCount(String keyword) {
		return bookDao.getBookCount(keyword);
	}
	
}
