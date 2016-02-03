package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.backend.factory.BeanFactory;
import com.sina.cms.backend.factory.DAOFactory;

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
				DAOFactory.fillDaoBySetMethod(method, this);
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
