package com.cxb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxb.pojo.UserInfo;
import com.cxb.service.UserInfoService;
import com.cxb.service.impl.UserInfoServiceImpl;

public class UserServlet extends HttpServlet {
	private UserInfoService userService = new UserInfoServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		path = path.substring(20, path.length());
		if(path.equals("checkUserName")) {//验证用户名是否已注册
			checkUserName(req,resp);
		}else if(path.equals("register")) {//用户注册
			register(req,resp);
		}else if(path.equals("login")) {//用户登录
			login(req,resp);
		}else if(path.equals("logout")) {//注销登录
			logout(req,resp);
		}
		
	}
	
	/**
	 * 用户注销登录
	 * @param req
	 * @param resp
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("user");
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	/**
	 * 用户登录验证
	 * @param req
	 * @param resp
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		String pwd = req.getParameter("passWord");
		UserInfo user = userService.getUserByUser(new UserInfo(name, pwd, null));
		if(user!=null) {
			//登录成功
			//重定向到首页
			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath()+"/book/getbooks");
		}else {
			//登录失败
			//转发到登录页面，并提示用户错误信息，并回显用户名
			req.setAttribute("mesage", "用户名密码不匹配！");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	/**
	 * 注册用户
	 * @param req
	 * @param resp
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		String pwd = req.getParameter("passWord");
		String email = req.getParameter("email");
		UserInfo user = new UserInfo(name,pwd,email);
		int i = userService.addUser(user);
		if(i==0) {
			//注册失败
			req.setAttribute("message", "服务器出现了未知错误，请重新注册！");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}else {
			//注册成功，直接免登录，可以去到首页或者用户中心
			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath()+"/register_success.jsp");
		}
	}

	/**
	 * 验证用户名是否已注册
	 * @param req
	 * @param resp
	 */
	private void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		UserInfo uf = userService.getUserByUser(new UserInfo(name,null,null));
		
		if(uf==null) {
			//返回1表示可用，0表示不可以用
			resp.getWriter().write("1");
		}else {
			resp.getWriter().write("0");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

