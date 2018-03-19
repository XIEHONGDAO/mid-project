package com.xyd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xyd.bean.UserInfo;
import com.xyd.service.UserService;

/**
 * 用户 控制器
 * 
 * @author Administrator
 * 
 * 男 未婚: @123.com
 * 
 *
 */
public class UserServlet extends HttpServlet {

	//user的 业务类
	private UserService userService = new  UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 获取请求的 uri
		String uri = req.getRequestURI();
		// 拿到项目的路径
		String contextPath = req.getContextPath();

//		System.out.println("uri " + uri);
//		System.out.println("contextPath " + contextPath);
		//只有要 url-param
//		System.out.println(uri.substring(contextPath.length() + 1));
		
		//拿到请求的path
		String path = uri.substring(contextPath.length() + 1);
		
	    //判断 user 的操作
		if (path.equals("user/checkname")) {
			
			//检查 用户名是否 存
			checkName(req,resp);
		}else if (path.equals("user/adduser")) {
			//注册的操作
			register(req,resp);
			
		}else if(path.equals("user/login")) {
			
			//登录操作
			login(req,resp);
			
		}else if(path.equals("user/logout")) {
			
			//注销的操作
			logout(req,resp);
			
		}
		
		
	}

	/**
	 * 注销的操作
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//保存在session中
		req.getSession().removeAttribute("user");
		resp.sendRedirect("/bookstore/book/getBooks");
	}

	/**
	 * 登录操作  
	 * 
	 * req    HttpServletRequest
	 * resp   HttpServletResponse 
	 * @throws IOException 
	 * 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String username = req.getParameter("userName");
		String password = req.getParameter("passWord");
		
		//创建对象 
		UserInfo userInfo = new UserInfo(username, username, null);
		
		//业务层操作
		UserInfo u = userService.login(userInfo);
		
		if (u != null) {
			//保存在session中
			req.getSession().setAttribute("user", u);
			resp.sendRedirect("/bookstore/book/getBooks");
		}
		
		
	}

	/**
	 * 用户的注册操作 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("userName");
		String password = req.getParameter("passWord");
		String email = req.getParameter("email");

		UserInfo userInfo = new UserInfo(userName, password, email);
		
		System.out.println(userInfo);
		
		// 1  就是注册成功
		int result = userService.addUserInfo(userInfo);
		
		if (result == 1) {
			
			//注册功能  到  注册成成成功的界面区 
			req.getRequestDispatcher("/register_success.jsp").forward(req, resp);
			
		}else{
			
			//重定向  注册界面
			resp.sendRedirect("/bookstore/register.jsp");
			
		}
	}

	/**
	 * 判断 注册用户是否 存在
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String userName = req.getParameter("userName");
		
		System.out.println(userName);
		
		UserInfo user = new UserInfo(userName, null, null);
		//user 的业务操作 
		int result = userService.checkName(user);
		
		System.out.println(result);
		//没有这个名字
		if (result == 0) {
			resp.getWriter().write("0");
		}else{
			//存在这个名字
			resp.getWriter().write("1");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
