package com.sina.cms.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.model.ArticleModel;

public class ArticleDAOTest {

	private static ArticleDAO dao;

	@BeforeClass
	public static void setup() {
		dao = new ArticleDAO();
	}

	@Test
	public void queryArticle() {
		ArticleModel model = dao.queryArticle(5, true);
		System.out.println(model);
	}

	@Test
	public void addArticle() {

		for (int i = 0; i < 100; i++) {
			ArticleModel article = new ArticleModel();
			article.setTitle("你好,第一篇" + i);
			article.setContent("这是一篇完整文章的内容！");
			article.setOwnerUserId("admin");
			article.setOriginUrl("http://demo.alibaba-inc.com/system/vds_extracted/d9/59/07/31/fe49a7a51e0b28d4a192848e/index.html");
			article.setStatus(1);
			article.setReplyNums(15);
			article.setClickNums(1501);
			article.setCreateDate(new Date(System.currentTimeMillis() - 24 * 60
					* 60 * 1000));
			article.setUpdateDate(new Date());
			article.setPublishDate(new Date());

			boolean res = dao.addArticle(article);
			Assert.assertTrue(res);
		}
	}

	@Test
	public void queryArticleList() {
		List<ArticleModel> list = dao.queryArticleList(20, 0, null);
		System.out.println(list.size());
	}

	@Test
	public void queryArticleCount() {
		System.out.println(dao.queryCountArticle(null));
	}

	@Test
	public void deleteArticle() {
		String[] tids = new String[]{"1"};
		System.out.println(dao.deleteArticles(tids));
	}

	@Test
	public void updateArticle() {
		ArticleModel article = new ArticleModel();
		article.setTitle("你好,第2222一篇");
		article.setContent("这是一篇完整文章的内容！");
		article.setOwnerUserId("admin");
		article.setOriginUrl("http://www.baidu.com");
		article.setStatus(1);
		article.setReplyNums(15);
		article.setClickNums(1501);
		article.setCreateDate(new Date(System.currentTimeMillis() - 24 * 60
				* 60 * 1000));
		article.setUpdateDate(new Date());
		article.setPublishDate(new Date());
		article.setTid(6);

		boolean res = dao.updateArticle(article);
		System.out.println(res);
	}
}
