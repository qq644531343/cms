package com.sina.cms.test.spring;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sina.cms.backend.factory.MyBatisSqlSessionFactory;
import com.sina.cms.backend.factory.SpringContext;

public class TestSpring {
	
	@Test
	public void testStart () {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = (Person)context.getBean("person");
		System.out.println(person);
		System.out.println(person.getName());
	}
	
	@Test 
	public void testSavePerson() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonDao dao = (PersonDao)context.getBean("personDao");
		dao.savePerson();
	}
	
	@Test
	public void testInteliger() {
		
		SqlSession session =  MyBatisSqlSessionFactory.openSession();
		System.out.println(session);
		session.close();
		
		ApplicationContext context = SpringContext.getContext();
		System.out.println(context);
	}
}
