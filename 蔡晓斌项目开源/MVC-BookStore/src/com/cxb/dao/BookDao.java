package com.cxb.dao;

import java.util.List;

import com.cxb.pojo.Book;

public interface BookDao {
	/**
	 * 修改书籍信息
	 * @param book 书籍信息
	 * @return
	 */
	int updateBook(Book book);
	/**
	 * 根据书名模糊查询书籍信息
	 * @return
	 */
	List<Book> getBooksByBookName(String bname,int min,int max);
	/**
	 * 分页查询
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	List<Book> getBooks(int min,int max);
	/**
	 * 查询书籍总数
	 * @return
	 */
	int getBooksNumber(String bname);
	/**
	 * 根据书的主键查询书籍
	 * @param bid 书的主键
	 * @return
	 */
	Book getBookById(int bid);
}