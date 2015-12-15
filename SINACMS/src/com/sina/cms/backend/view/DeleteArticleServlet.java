package com.sina.cms.backend.view;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.utils.StringUtils;

/**
 * Servlet implementation class DeleteArticleServlet
 */
@WebServlet("/backend/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticleServlet() {
        super();
 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] tidsString = request.getParameterValues("tid");
		
		boolean res = false;
		
		if (tidsString.length > 0) {
			res = new ArticleDAO().deleteArticles(tidsString);
		}
		
		if (res) {
			System.out.println("删除成功:tid:" +" tids:" + tidsString);
		}else {
			System.out.println("删除失败:tid:" + " tids:"+ tidsString);
		}
		//跳转文章列表
		request.getRequestDispatcher("/backend/SearchArticleServlet").forward(request, response);
	}
	
}
