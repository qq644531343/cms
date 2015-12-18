package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDAO;
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
		
		String offsetstrString = request.getParameter("pager.offset");
		String pageSizeString =  request.getParameter("pagesize");
		String title = request.getParameter("title");
		if (pageSizeString == null) {
			pageSizeString = (String) request.getSession().getAttribute("pagesize");
		}
		
		int offset = 0, pagesize = 5;
		if (offsetstrString != null) {
			offset = Integer.parseInt(offsetstrString);
		}
		if(pageSizeString != null) {
			pagesize = Integer.parseInt(pageSizeString);
		}
		
		request.getSession().setAttribute("pagesize", pagesize+"");
		
		//查询文章列表
		ArticleDAO dao = new ArticleDAO();
		List<ArticleModel> articles = dao.queryArticleList(pagesize, offset, title);
		int total = dao.queryCountArticle(title);
		
		request.setAttribute("articles", articles);
		request.setAttribute("total", total);
		
		System.out.println("查询文章列表");
		//跳转文章列表
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
