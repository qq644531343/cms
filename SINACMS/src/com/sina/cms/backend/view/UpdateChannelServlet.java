package com.sina.cms.backend.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDAO;
import com.sina.cms.dao.ChannelDAO;
import com.sina.cms.model.ArticleModel;
import com.sina.cms.model.ChannelModel;
import com.sina.cms.utils.StringUtils;

@WebServlet("/backend/UpdateChannelServlet")
public class UpdateChannelServlet extends HttpServlet {

	private static final long serialVersionUID = -478700269882136429L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("更新频道");
		
		ChannelModel channel = (ChannelModel) request.getSession().getAttribute("channel");
		System.out.println(channel);
		
		String title = request.getParameter("title");
		String content = request.getParameter("description");
		
		if (StringUtils.isEmpty(title)) {
			this.forward(false, "标题不能为空", request, response);
			return;
		}
		
		channel.setTitle(title);
		channel.setDescription(content);
		
		boolean res = new ChannelDAO().updateChannel(channel);
		
		if (!res) {
			this.forward(false, "服务器故障", request, response);
			return;
		}
		
		this.forward(true, "修改频道成功", request, response);
	}

	private void forward(boolean success, String msg,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("update", true);
		request.setAttribute("result", success);
		if (success) {
			request.setAttribute("message", msg);
			request.getRequestDispatcher(
					"/backend/channel/add_channel_success.jsp").forward(
					request, response);
		} else {
			request.setAttribute("error", "修改文章失败，" + msg);
			request.getRequestDispatcher("/error/error.jsp").forward(request,
					response);
		}
	}

}
