package com.xyd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xyd.bean.Book;
import com.xyd.service.BookService;

public class BookServlet extends HttpServlet{

	private BookService bookService = new BookService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//判断 path路径
		
		// 获取请求的 uri
		String uri = req.getRequestURI();
		// 拿到项目的路径
		String contextPath = req.getContextPath();
		
		//拿到请求的path
		String path = uri.substring(contextPath.length() + 1);
		
		if (path.equals("book/getBooks")) {
			
			//查询书籍的操作
			queryBook(req,resp,null);
			
		}else if(path.equals("book/searchBook")){
			
			//模糊查询书籍
			queryLikeBook(req,resp);
			
		}
	}
	
	/**
	 * 默认查询书籍
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void queryLikeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keywords = req.getParameter("keywords");
		
		System.out.println("keywords : " + keywords);
		
		//查询书籍
		queryBook(req, resp, keywords);
		
	}

	/**
	 * 查询书籍
	 * @param req
	 * @param resp
	 * @param String  模糊查询书籍
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void queryBook(HttpServletRequest req, HttpServletResponse resp, String keyword) throws ServletException, IOException {
		
		//获取请求传递的数据 
		String position = req.getParameter("index");
		
		System.out.println("position : " + position);
				
		//初始化 页面   默认给1
		int index = 1;
		int pageSize = 3;
		//当前的 界面
		if (position != null ) {
			index = Integer.valueOf(position);
		}

		//获取数据
		List<Book> books = null;
		int count = 0;
		//模糊查询
		if (keyword != null && keyword.length() > 0 ) {
			
			books = bookService.getBooks(index, pageSize,keyword);
			//拿到 书本的总数  
			count = bookService.getBookCount(keyword);
			
		}else{
			//获取数据
			books = bookService.getBooks(index, pageSize);
			//拿到 书本的总数  
			count = bookService.getBookCount();
		}
		
		//一共多少页
		int size = 0;
		if (count % pageSize == 0) {
			size = count / pageSize;
		}else{
			size = count / pageSize + 1; 
		}
		
		System.out.println("一共有多少条数据 :" + count + "当前页数是 :" + index + "一共有多少页:" + pageSize );
		
		
		req.setAttribute("index", index);
		req.setAttribute("size", size);
		
		//把数据 传到 index.jsp 界面
		req.setAttribute("books", books);
		
		//请求转发到  index.jsp; 
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		this.doGet(req, resp);
	}
}
