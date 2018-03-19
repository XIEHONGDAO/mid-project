package com.cxb.utils;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cxb.pojo.UserInfo;
public class PowerFilter implements Filter {
	private String nofilter;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		//判断访问页面是否需要过滤
		String path = request.getServletPath();
		//将不需要过滤的servletPath转成数组
		String nofilters[] = nofilter.split(",");
		if(Arrays.asList(nofilters).contains(path)) {
			//不需要过滤的path 直接放行
			filterchain.doFilter(request, response);
		}else {
			UserInfo user = (UserInfo)request.getSession().getAttribute("user");
			if(user==null) {
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}else {
				filterchain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		nofilter = filterconfig.getInitParameter("nofilter");
	}

}
