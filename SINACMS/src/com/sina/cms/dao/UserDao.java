package com.sina.cms.dao;

import com.sina.cms.model.UserModel;

public interface UserDao {
	
	public boolean addUser(UserModel user) ;
	
	public boolean updateUser(UserModel user);
	
	public UserModel queryUser(String username);
	
	public boolean deleteUser(String username);
}
