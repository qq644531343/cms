package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.dao.UserDAO;
import com.sina.cms.model.ArticleModel;
import com.sina.cms.model.UserModel;
import com.sina.cms.utils.StringUtils;

/**
 * Servlet implementation class AddArticleServlet
 */
@WebServlet("/backend/AddArticleServlet")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddArticleServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("添加文章");
		String title = request.getParameter("title");
		String source = request.getParameter("source");
		String content = request.getParameter("content");
		UserModel user = (UserModel)request.getSession().getAttribute("user");
		
		if (StringUtils.isEmpty(title)) {
			this.forward(false, "标题不能为空", request, response);
			return;
		}
		if(StringUtils.isEmpty(content)) {
			this.forward(false, "内容不能为空", request, response);
			return;
		}
		ArticleModel article = new ArticleModel();
		article.setTitle(title);
		article.setContent(content);
		article.setOriginUrl(source);
		article.setCreateDate(new Date());
		article.setUpdateDate(new Date());
		article.setClickNums(0);
		article.setReplyNums(0);
		if(user != null) {
			article.setOwnerUserId(user.getUsername());
		}else {
			article.setOwnerUserId("unknown");
		}
		article.setStatus(0);
		boolean res = new ArticleDAO().addArticle(article);
		
		if (!res) {
			this.forward(false, "服务器故障", request, response);
			return;
		}
		
		this.forward(true, "添加文章成功", request, response);
	}
	
	private void forward(boolean success, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("result", success);
		if(success) {
			request.setAttribute("message", msg);
		}else {
			request.setAttribute("message", "添加文章失败，" +msg);
		}
		
		request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
	}

}
