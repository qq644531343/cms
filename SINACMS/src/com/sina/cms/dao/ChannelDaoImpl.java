package com.sina.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sina.cms.model.ChannelModel;
import com.sina.cms.utils.DBUtil;
import com.sina.cms.utils.StringUtils;

public class ChannelDaoImpl implements ChannelDao{
	
	public List<ChannelModel> queryChannelList(int pageSize, int offset, String titleKey) {
		List<ChannelModel> list = new ArrayList<ChannelModel>();
		
		Connection connection = DBUtil.getConnection();
		ResultSet set = null;
		Statement stmt = null;
		
		String sql = "select * from channel order by cid desc limit " + offset + "," + pageSize;
		if (StringUtils.isNotEmpty(titleKey)) {
			sql = "select * from channel where title like '%"+titleKey+"%' order by cid desc limit " + offset + "," + pageSize;
		}
		try {
			stmt = connection.createStatement();
			set = stmt.executeQuery(sql);
			while(set.next()) {
				ChannelModel channel = new ChannelModel();
				channel.setCid(set.getLong("cid"));
				channel.setTitle(set.getString("title"));
				channel.setDescription(set.getString("description"));
				list.add(channel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(set);
			DBUtil.close(stmt);
			DBUtil.close(connection);
		}
		return list;
	}
	
	public ChannelModel queryChannel(int cid) {
		ChannelModel channel = null;
		
		Connection connection = DBUtil.getConnection();
		ResultSet set = null;
		Statement stmt = null;
		
		String sql = "select * from channel where cid= " + cid;
		
		try {
			stmt = connection.createStatement();
			set = stmt.executeQuery(sql);
			if(set.next()) {
				channel = new ChannelModel();
				channel.setCid(set.getLong("cid"));
				channel.setTitle(set.getString("title"));
				channel.setDescription(set.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(set);
			DBUtil.close(stmt);
			DBUtil.close(connection);
		}
		return channel;
	}
	
	public int queryCountChannel(String titleKey) {
		int count = 0;
		Connection connection = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet set = null;
		String sql = "select count(*) from channel";
		if (StringUtils.isNotEmpty(titleKey)) {
			sql = "select count(*) from channel where title like '%"+titleKey+"%'";
		}
		
		try {
			stmt = connection.createStatement();
			set = stmt.executeQuery(sql);
			
			if (set.next()) {
				count = set.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(set);
			DBUtil.close(stmt);
			DBUtil.close(connection);
		}
		return count;
	}
	
	public boolean addChannel(ChannelModel channel) {
		boolean result = false;
		if (StringUtils.isEmpty(channel.getTitle()) ) {
			return result;
		}
		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into channel (title,description) values (?, ?)";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, channel.getTitle());
			pstmt.setString(2, channel.getDescription());
			System.out.println(pstmt);
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(connection);
		}
		return result;
	}
	
	public boolean deleteChannels(String[] cids) {
		boolean result = false;
		Connection connection = DBUtil.getConnection();
		Statement stmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append("delete from channel where");
		for(int i = 0; i < cids.length; i ++ ) {
			sb.append(" cid=" + cids[i] + " or");
		}
		sb.delete(sb.length()-2, sb.length());
		System.out.println(sb.toString());
		try {
			stmt = connection.createStatement();
			int res = stmt.executeUpdate(sb.toString());
			if(res > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(connection);
		}
		return result;
	} 
	
	public boolean updateChannel(ChannelModel channel) {
		boolean result = false;
		
		if (StringUtils.isEmpty(channel.getTitle()) ) {
			return result;
		}
		
		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update channel set title=?, description=? where cid=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, channel.getTitle());
			pstmt.setString(2, channel.getDescription());
			pstmt.setLong(3, channel.getCid());
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(connection);
		}
		
		return result;
	}
}
