package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.dao.ArticleDaoImpl;
import com.sina.cms.dao.ChannelDaoImpl;
import com.sina.cms.model.ChannelModel;
import com.sina.cms.utils.StringUtils;

/**
 * Servlet implementation class addChannelServlet
 */
@WebServlet("/backend/AddChannelServlet")
public class AddChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChannelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("添加频道");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		if (StringUtils.isEmpty(title)) {
			this.forward(false, "标题不能为空", request, response);
			return;
		}
		
		ChannelModel channel = new ChannelModel();
		channel.setTitle(title);
		channel.setDescription(description);
		
		boolean res = new ChannelDaoImpl().addChannel(channel);
		
		if (!res) {
			this.forward(false, "服务器故障", request, response);
			return;
		}
		
		this.forward(true, "添加频道成功", request, response);	
	}
	
	private void forward(boolean success, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("result", success);
		if(success) {
			request.setAttribute("message", msg);
			request.getRequestDispatcher("/backend/channel/add_channel_success.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "添加频道失败，" +msg);
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}
	}

}
