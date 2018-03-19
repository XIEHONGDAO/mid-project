package com.xyd.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xyd.bean.Book;
import com.xyd.dao.BookDao;
import com.xyd.daoimpl.BookDaoImpl;

public class BookDaoImplTest {

	private BookDao bookDao = null;
	//初始化操作 
	@Before
	public void setUp() throws Exception {
		
		bookDao = new BookDaoImpl();
		
		System.out.println("初始化操作");
	}

	//释放资源
	@After
	public void tearDown() throws Exception {
		
		bookDao = null;
		System.out.println("释放资源");
	}

	@Test
	public void testGetBook() {
		
		List<Book> books = bookDao.getBook(6, 4);
		
		for (Book book : books) {
			System.out.println(book);
		}
	}

	@Test
	public void testCount(){
		System.out.println(bookDao.getBookCount("生"));
	}
	
	@Test
	public void testGetBookCount() {
	}

}
