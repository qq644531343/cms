package com.sina.cms.dao;

import java.util.List;

import com.sina.cms.model.ArticleModel;

public interface ArticleDao {
	
	public boolean addArticle(ArticleModel article) ;
	
	public ArticleModel querySimpleArticle(int tid);
	
	public ArticleModel queryArticle(int tid, boolean withContent);
	
	public List<ArticleModel> queryArticleList(int pageSize, int offset, String titleKey);
	
	public int queryCountArticle(String titleKey) ;
	
	public boolean deleteArticles(String[] tids);
	
	public boolean updateArticle(ArticleModel article) ;
}
