package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.backend.factory.PropertiesDaoFactory;
import com.sina.cms.utils.StringUtils;

public class InitBeanFactoryServlet extends HttpServlet {
	
	public static String INIT_DAO_FACTORY = "_my_dao_factory";
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String location = config.getInitParameter("configLocation");
		
		PropertiesDaoFactory factory = null;
		if (StringUtils.isNotEmpty(location)) {
			factory = new PropertiesDaoFactory(location);
		}else {
			factory = new PropertiesDaoFactory();
		}
		config.getServletContext().setAttribute(INIT_DAO_FACTORY, factory);
		
		super.init(config);
	}
}
