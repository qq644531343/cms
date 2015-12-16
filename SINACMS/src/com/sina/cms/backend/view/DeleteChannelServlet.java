package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.dao.ChannelDAO;

@WebServlet("/backend/DeleteChannelServlet")
public class DeleteChannelServlet extends HttpServlet {

	private static final long serialVersionUID = -7860215894573462886L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] tidsString = request.getParameterValues("cid");

		boolean res = false;

		if (tidsString!=null && tidsString.length > 0) {
			res = new ChannelDAO().deleteChannels(tidsString);
		}

		if (res) {
			System.out.println("删除成功:cid:" + " tids:" + tidsString);
		} else {
			System.out.println("删除失败:cid:" + " tids:" + tidsString);
		}
		// 跳转文章列表
		request.getRequestDispatcher("/backend/SearchChannelServlet").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
