package com.sina.cms.backend.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	
	private static ApplicationContext context = null;
	
	public static ApplicationContext getContext() {
		
		if (context == null) {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return context;
	}
	
	public void closeSpring() {
		if (context instanceof ClassPathXmlApplicationContext) {
			ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext)context;
			applicationContext.close();
		}
	}
	
	
}
