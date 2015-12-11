package com.sina.cms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.utils.*;


public class BaseFilter implements Filter {
	
	private String excludedPages;  
	private String[] excludedPageArray;

    public BaseFilter() {
       
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//有过滤器时，需第一行设置编码，否则会出现各种乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
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
		if (!StringUtils.isEmpty(excludedPages)) {
			excludedPageArray = excludedPages.split(",");
		}
	}
	
	private boolean filter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		return true;
	}

}
