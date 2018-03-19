package com.cxb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxb.pojo.Book;
import com.cxb.pojo.Item;
import com.cxb.pojo.UserInfo;
import com.cxb.service.BookService;
import com.cxb.service.OrderService;
import com.cxb.service.impl.BookServiceImpl;
import com.cxb.service.impl.OrderServiceImpl;

public class OrderServlet extends HttpServlet{
	private OrderService orderService = new OrderServiceImpl();
	private BookService bookService = new BookServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		path = path.substring(21, path.length());
		//获取当前当如的用户名
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
//		if(user==null) {
//			//用户未登录，重定向到登录页面
//			resp.sendRedirect(req.getContextPath()+"/login.jsp");
//			return;
//		}
		if (path.equals("shoppingcar")) {// 分页查询订单信息
			shoppingcar(req, resp);
		} else if(path.equals("addOrder")) {//订单添加到购物车，状态为0
			addOrder(req,resp);
		} else if(path.equals("orderpay")) {//购物车付款
			orderPay(req,resp);
		} else if(path.equals("orderlist")) {//订单查询
			orderList(req,resp);
		}
	}
	
	/**
	 * 查询用户订单
	 * @param req
	 * @param resp
	 */
	private void orderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前当如的用户名
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		//获取用户输入的关键字查询
		String keywords = req.getParameter("keywords");
		Item item= null;
		if(keywords!=null && !"".equals(keywords)) {
			//根据用户输入的内容查询对应的订单
			String regEx = "[0-9]*";
			// 编译正则表达式
		    Pattern pattern = Pattern.compile(regEx);
		    // 忽略大小写的写法
		    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(keywords);
		    // 字符串是否与正则表达式相匹配
		    boolean rs = matcher.matches();
		    item = new Item();
		    if(rs) {
		    	//订单号
		    	item.setOid(Long.parseLong(keywords));
		    }else {
		    	Book book = new Book();
		    	book.setBname(keywords);
		    	item.setBook(book);
		    }
		}
		//查询订单状态为不等于0的
		List<Item> list = orderService.getItem(user.getUsername(), item,1);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/orderlist.jsp").forward(req, resp);
	}

	/**
	 * 购物车结算
	 * @param req
	 * @param resp
	 */
	private void orderPay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String iids[] = req.getParameterValues("iid");
		String bids[] = req.getParameterValues("bid");
		String nums[] = req.getParameterValues("nums");
		List<Item> list = new ArrayList<Item>();
		for(int i=0;i<iids.length;i++) {
//			System.out.println(iids[i]+"-->"+nums[i]);
			Item item = new Item(Long.parseLong(iids[i]), Integer.parseInt(bids[i]), Integer.parseInt(nums[i]), 0);
			list.add(item);
		}
		orderService.updateItem(list);
		resp.sendRedirect(req.getContextPath()+"/shopping-success.jsp");
	}

	/**
	 * 添加到购物车
	 * @param req
	 * @param resp
	 */
	private void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前当如的用户名
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		//获取复选框提交的内容
		String bids[] = req.getParameterValues("bookId");
		//得到书籍的信息集合
		List<Item> items = new ArrayList<Item>();
		double total_price = 0;
		for(String bid:bids) {
			Book book = bookService.getBookById(Integer.parseInt(bid));
			Item item = new Item(book.getBid(), 1, book.getBprice(), 0, book);
			total_price+=book.getBprice();
			if(book.getStock()<1) {
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
			items.add(item);
		}
		orderService.addOrder(user.getUsername(), items,total_price);
		//查询用户购物车的内容
		List<Item> list = orderService.getItem(user.getUsername(), null,0);
		//订单添加成功去到购物车
		req.setAttribute("items", list);
		req.getRequestDispatcher("/shopping.jsp").forward(req, resp);
	}

	/**
	 * 查询购物车
	 * @param req
	 * @param resp
	 */
	private void shoppingcar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//用户
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		System.out.println("现在登录的用户是："+user);
		List<Item> items = orderService.getItem(user.getUsername(), null,0);
		req.setAttribute("items", items);
		req.getRequestDispatcher("/shopping.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
