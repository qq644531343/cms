package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.dao.ChannelDAO;
import com.sina.cms.model.ArticleModel;
import com.sina.cms.model.ChannelModel;

/**
 * Servlet implementation class SearchChannelServlet
 */
@WebServlet("/backend/SearchChannelServlet")
public class SearchChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchChannelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查询文章列表
		List<ChannelModel> channels = new ChannelDAO().queryChannelList(5, 0);

		request.setAttribute("channels", channels);
		System.out.println("查询频道列表");
		// 跳转文章列表
		request.getRequestDispatcher("/backend/channel/channel_list.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
