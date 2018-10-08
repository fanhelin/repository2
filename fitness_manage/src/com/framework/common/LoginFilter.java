package com.framework.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.interceptor.AllInterceptor;
import com.wx.entity.sys.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String url = ((HttpServletRequest)arg0).getRequestURI() ;
		if(AllInterceptor.isExclude(url)) 
		{
			arg2.doFilter(arg0,arg1);
			return;
		}
		User user=(User) ((HttpServletRequest)arg0).getSession().getAttribute(StaticFinal.SESSION_KEY_USER);
		if (user == null) {
			((HttpServletResponse)arg1).sendRedirect("/fitness_manage/index.jsp");
			return;
		}
		arg2.doFilter(arg0,arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
