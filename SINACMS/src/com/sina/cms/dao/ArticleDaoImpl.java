package com.sina.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sina.cms.backend.factory.MyBatisSqlSessionFactory;
import com.sina.cms.backend.tools.DateTool;
import com.sina.cms.model.ArticleModel;
import com.sina.cms.utils.DBUtil;
import com.sina.cms.utils.StringUtils;

public class ArticleDaoImpl  implements ArticleDao{
	
	public boolean addArticle(ArticleModel article) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		session.insert(ArticleDao.class.getName() + ".addArticle", article);
		session.commit();
		session.close();
		return true;
	}

	public ArticleModel querySimpleArticle(int tid) {
		return this.queryArticle(tid, false);
	}

	public ArticleModel queryArticle(int tid, boolean withContent) {

		ArticleModel article = null;

		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;

		String sql = null;
		if (withContent) {
			sql = "select * from article where tid=?";
		} else {
			sql = "select tid,title,originUrl,clickNums,replyNums,"
					+ "createDate,updateDate,publishDate,status,ownerUsername"
					+ "from article where tid=?";
		}

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, tid);
			set = pstmt.executeQuery();

			if (set.next()) {
				article = this.getArticleModelFroSet(set, withContent);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(set);
			DBUtil.close(pstmt);
			DBUtil.close(connection);
		}

		return article;
	}

	public List<ArticleModel> queryArticleList(int pageSize, int offset, String titleKey) {

		ArrayList<ArticleModel> list = new ArrayList<ArticleModel>();

		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;

		String sql = "select tid,title,originUrl,clickNums,replyNums,"
				+ "createDate,updateDate,publishDate,status,ownerUsername"
				+ " from article order by tid desc limit ?,?";
		if (StringUtils.isNotEmpty(titleKey)) {
			sql = "select tid,title,originUrl,clickNums,replyNums,"
					+ "createDate,updateDate,publishDate,status,ownerUsername"
					+ " from article where title like '%"+titleKey+"%' order by tid desc limit ?,?";
		}

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, pageSize);
			set = pstmt.executeQuery();
			System.out.println(pstmt);
			while (set.next()) {
				list.add(this.getArticleModelFroSet(set, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(set);
			DBUtil.close(pstmt);
			DBUtil.close(connection);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int queryCountArticle(String titleKey) {
		int count = 0;
		Connection connection = DBUtil.getConnection();
		Statement stmt = null;
		ResultSet set = null;
		
		String sql = "select count(*) from article";
		if(StringUtils.isNotEmpty(titleKey)){
			sql = "select count(*) from article where title like '%"+titleKey+"%'";
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
	
	public boolean deleteArticles(String[] tids) {
		boolean result = false;
		if (tids.length == 0) {
			return result;
		}
		
		Connection connection = DBUtil.getConnection();
		Statement stmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append("delete from article where");
		for(int i = 0; i < tids.length; i ++ ) {
			sb.append(" tid=" + tids[i] + " or");
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
	
	public boolean updateArticle(ArticleModel article) {
		boolean result = false;
		Connection connection = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update article "
				+ "set title=?,originUrl=?,clickNums=?,replyNums=?,createDate=?,"
				+ "updateDate=?,publishDate=?,status=?,ownerUsername=?,content=?"
				+ " where tid=?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getOriginUrl());
			pstmt.setLong(3, article.getClickNums());
			pstmt.setInt(4, article.getReplyNums());
			pstmt.setTimestamp(5, DateTool.javaDateToTimestamp(article.getCreateDate()));
			pstmt.setTimestamp(6, DateTool.javaDateToTimestamp(article.getUpdateDate()));
			pstmt.setTimestamp(7, DateTool.javaDateToTimestamp(article.getPublishDate()));
			pstmt.setInt(8, article.getStatus());
			pstmt.setString(9, article.getOwnerUserId());
			pstmt.setString(10, article.getContent());
			pstmt.setLong(11, article.getTid());

			int res = pstmt.executeUpdate();
			if (res == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(connection);
		}
		return result;
	}

	private ArticleModel getArticleModelFroSet(ResultSet set,
			boolean withContent) throws SQLException {
		ArticleModel article = new ArticleModel();
		article.setTid(set.getInt("tid"));
		article.setTitle(set.getString("title"));
		article.setOriginUrl(set.getString("originUrl"));
		article.setClickNums(set.getInt("clickNums"));
		article.setReplyNums(set.getInt("replyNums"));
		article.setCreateDate(DateTool.timestampToJavaDate(set.getTimestamp("createDate")));
		article.setUpdateDate(DateTool.timestampToJavaDate(set.getTimestamp("updateDate")));
		article.setPublishDate(DateTool.timestampToJavaDate(set.getTimestamp("publishDate")));
		article.setStatus(set.getInt("status"));
		article.setOwnerUserId(set.getString("ownerUsername"));
		if (withContent) {
			article.setContent(set.getString("content"));
		}
		return article;
	}
}
