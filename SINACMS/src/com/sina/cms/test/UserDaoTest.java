package com.sina.cms.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sina.cms.dao.UserDAO;
import com.sina.cms.model.UserModel;

public class UserDaoTest {
	
	private static UserDAO dao = null;
	
	@BeforeClass
	public static void setup () {
		dao = new UserDAO();
	}

	@Test
	public void testQuery () {
		UserModel user = dao.queryUser("admin");
		Assert.assertNotNull(user);
		System.out.println(user);
	}
}
