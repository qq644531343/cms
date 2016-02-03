package com.sina.cms.aspect;

import org.apache.ibatis.session.SqlSession;

import com.sina.cms.backend.factory.MyBatisSqlSessionFactory;

public class MyBatisSessionAspect {
	private SqlSession session = null;
	
	public void openSession () {
		session = MyBatisSqlSessionFactory.openSession();
		System.out.println("打开 " + session);
	}
	
	public void closeSession() {
		System.out.println("关闭 " + session);
		if (session != null) {
			session.close();
		}
	}
}
