package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.backend.factory.BeanFactory;

import java.lang.reflect.*;

public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BeanFactory factory = (BeanFactory) getServletContext().getAttribute(InitBeanFactoryServlet.INIT_DAO_FACTORY);
		
		Method[] methods = this.getClass().getMethods();
		
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				String methodName = method.getName().substring(3);
				StringBuilder sb = new StringBuilder(methodName);
				sb.replace(0, 1, (sb.charAt(0) + "").toLowerCase());
				System.out.println(sb.toString());
				Object daoBean = factory.getBean(sb.toString());
				try {
					method.invoke(this, daoBean);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		super.doPost(req, resp);
	}

}
