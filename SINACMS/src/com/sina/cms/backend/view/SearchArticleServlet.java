package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.model.ArticleModel;

/**
 * Servlet implementation class SearchArticleServlet
 */
@WebServlet("/backend/SearchArticleServlet")
public class SearchArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//查询文章列表
		List<ArticleModel> articles = new ArrayList<ArticleModel>();
		for(int i =0 ; i < 10; i++) {
			
		}
		request.setAttribute("articles", articles);
		System.out.println("查询文章列表");
		//跳转文章列表
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
