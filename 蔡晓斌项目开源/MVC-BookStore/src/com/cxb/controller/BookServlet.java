package com.cxb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxb.pojo.Book;
import com.cxb.service.BookService;
import com.cxb.service.impl.BookServiceImpl;

public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		path = path.substring(20, path.length());
		System.out.println(path);
		if (path.equals("getbooks")) {// 分页查询书籍信息
			getBooks(req, resp);
		} else if (path.equals("searchBooks")) {// 根据书名模糊搜索书籍信息
			searchBooks(req, resp);
		}

	}

	/**
	 * 模糊查询
	 * 
	 * @param req
	 * @param resp
	 */
	private void searchBooks(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		// 获取用户输入的书名，进行模糊查询
		String name = req.getParameter("keywords");
		System.out.println(name + "------");
		queryBooks(req,resp,name);
	}

	/**
	 * 分页查询
	 * 
	 * @param req
	 * @param resp
	 */
	private void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		queryBooks(req,resp,null);
	}

	public void queryBooks(HttpServletRequest req, HttpServletResponse resp,String keyWords) throws ServletException, IOException {
		// 获取页数参数
		String index = req.getParameter("index");
		int page = 1;// 页数，默认显示第一页
		int pageSize = 3;// 每页显示3条数据
		if (index != null) {
			page = Integer.parseInt(index);
		}
		// 创建书籍业务操作对象
		BookService bookService = new BookServiceImpl();
		List<Book> list = null;
		// 获取书籍总数
		int count = 0;
		if(keyWords!=null) {
			//模糊查询
			list = bookService.getBooksByBookName(keyWords, page, pageSize);
			count = bookService.getBooksNumber(keyWords);
			req.setAttribute("url", "book/searchBooks");
		}else {
			list = bookService.getBooks(page, pageSize);
			count = bookService.getBooksNumber(null);
			req.setAttribute("url", "book/getbooks");
		}
		// 计算总页数
		int size = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);
		System.out.println("每页显示的数量：" + pageSize + " 总数量：" + count + " 总页数：" + size);
		req.setAttribute("count", size);
		req.setAttribute("books", list);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

