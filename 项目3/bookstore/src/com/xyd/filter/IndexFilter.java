package com.xyd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		         HttpServletRequest req = (HttpServletRequest) request;
		         HttpServletResponse resp = (HttpServletResponse) response;
		       
		         
//		        //index.jsp
//				String path = req.getServletPath();
//				
//				if (path.equals("/index.jsp")) {
		            
		            //第一次访问   不过过滤   index.jsp  是没有数据的  
		         
					//重定向   
					resp.sendRedirect("/bookstore/book/getBooks");
//				}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
