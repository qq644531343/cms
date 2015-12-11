package com.sina.cms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sina.cms.utils.*;

public class LoginFilter implements Filter {
	
	private String excludedPages;  
	private String[] excludedPageArray;

    public LoginFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		
		boolean isExcludePage = false;
		for (String page : excludedPageArray) {
			if (hRequest.getServletPath().startsWith(page)) {
				isExcludePage = true;
				break;
			}
		}
	
		if (isExcludePage) {
			chain.doFilter(request, response);
		}else {
			boolean res = filter(hRequest, hResponse);
			if (res) {
				chain.doFilter(request, response);
			}
		}	
	}

	public void init(FilterConfig fConfig) throws ServletException {
		excludedPages = fConfig.getInitParameter("excludedPages");
		if (! StringUtils.isEmpty(excludedPages)) {
			excludedPageArray = excludedPages.split(",");
		}
	}
	
	private boolean filter(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Object sign =  request.getSession().getAttribute("user");
		if (sign != null) {
			return true;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/login.jsp");
		response.setStatus(400);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return false;
	}

}
