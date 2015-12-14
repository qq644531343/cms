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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int tid = Integer.parseInt(request.getParameter("tid"));
		boolean res = new ArticleDAO().deleteArticle(tid);
		
		if (res) {
			System.out.println("删除成功:" + tid);
		}else {
			System.out.println("删除失败:" + tid);
		}
		//跳转文章列表
		request.getRequestDispatcher("/backend/SearchArticleServlet").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
