package com.cxb.service.impl;

import java.util.List;

import com.cxb.dao.BookDao;
import com.cxb.dao.impl.BookDaoImpl;
import com.cxb.pojo.Book;
import com.cxb.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> getBooksByBookName(String bname,int page,int pageSize) {
		int min = (page-1)*pageSize+1;
		int max = page*pageSize;
		return bookDao.getBooksByBookName(bname,min,max);
	}

	@Override
	public List<Book> getBooks(int page, int pageSize) {
		int min = (page-1)*pageSize+1;
		int max = page*pageSize;
		return bookDao.getBooks(min, max);
	}

	@Override
	public int getBooksNumber(String bname) {
		return bookDao.getBooksNumber(bname);
	}

	@Override
	public Book getBookById(int bid) {
		return bookDao.getBookById(bid);
	}

}
