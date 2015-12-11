package com.sina.cms.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("服务器部署");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("服务器停止");
	}

}