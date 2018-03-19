package com.cxb.service;

import java.util.List;

import com.cxb.pojo.Book;

public interface BookService {
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
	List<Book> getBooksByBookName(String bname,int page,int pageSize);
	/**
	 * 分页查询书籍信息
	 * @param page 页数
	 * @param pageSize 每页显示的数量
	 * @return
	 */
	List<Book> getBooks(int page,int pageSize);
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
