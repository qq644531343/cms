package com.sina.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sina.cms.model.UserModel;
import com.sina.cms.utils.DBUtil;
import com.sina.cms.utils.StringUtils;

public class UserDAO {

	public boolean addUser(UserModel user) {
		
		
		return true;
	}
	
	public boolean updateUser(UserModel user) {
		
		return true;
	}
	
	public UserModel queryUser(String username) {
		
		UserModel user = null;
		
		if (StringUtils.isEmpty(username)) {
			return user;
		}
		
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		
		try {
			 pStatement = connection.prepareStatement("select * from user where username=?");
			 pStatement.setString(1, username);
			 ResultSet set = pStatement.executeQuery();
			 if (set.next()) {
				user = new UserModel();
				user.setUsername(username);
				user.setPassword(set.getString(2));
				user.setBirthday(set.getString(3));
				user.setPrivilege(set.getString(4));
				user.setStatus(set.getInt(5));
				user.setSex(set.getString(6));
				user.setLastLoginTime(set.getString(7));
				user.setLastLoginIp(set.getString(8));
				user.setLoginTimes(set.getInt(9));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.close(pStatement);
		}
		return user;
	}
	
	public boolean deleteUser(String username) {
		
		return true;
	}
	
}
