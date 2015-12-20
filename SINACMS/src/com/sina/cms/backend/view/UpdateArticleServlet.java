package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDaoImpl;
import com.sina.cms.model.ArticleModel;
import com.sina.cms.model.UserModel;
import com.sina.cms.utils.StringUtils;

/**
 * Servlet implementation class UpdateArticleServlet
 */
@WebServlet("/backend/UpdateArticleServlet")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("更新文章");
		
		ArticleModel article = (ArticleModel) request.getSession().getAttribute("article");
		System.out.println(article);
		
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		
		if (StringUtils.isEmpty(title)) {
			this.forward(false, "标题不能为空", request, response);
			return;
		}
		if(StringUtils.isEmpty(content)) {
			this.forward(false, "内容不能为空", request, response);
			return;
		}
		
		article.setTitle(title);
		article.setContent(content);
		article.setOriginUrl(source);
		article.setUpdateDate(new Date());
		
		boolean res = new ArticleDaoImpl().updateArticle(article);
		
		if (!res) {
			this.forward(false, "服务器故障", request, response);
			return;
		}
		
		this.forward(true, "修改文章成功", request, response);
	}
	
private void forward(boolean success, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("update", true);
		request.setAttribute("result", success);
		if(success) {
			request.setAttribute("message", msg);
			request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "修改文章失败，" +msg);
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}
	}

}
