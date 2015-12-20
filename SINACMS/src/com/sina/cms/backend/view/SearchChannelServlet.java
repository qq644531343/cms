package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDaoImpl;
import com.sina.cms.dao.ChannelDaoImpl;
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
		
		String offsetstrString = request.getParameter("pager.offset");
		String pageSizeString = request.getParameter("pagesize");
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
		
		// 查询文章列表
		ChannelDaoImpl dao = new ChannelDaoImpl();
		List<ChannelModel> channels = dao.queryChannelList(pagesize, offset,title);
		int total = dao.queryCountChannel(title);
		
		request.setAttribute("channels", channels);
		request.setAttribute("total", total);
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
