package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/backend/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		
		String sessionCheckcode = (String) request.getSession().getAttribute("checkcode");
		
		//验证码不正确
		if (sessionCheckcode == null || checkcode == null || !sessionCheckcode.equalsIgnoreCase(checkcode)) {
			request.setAttribute("error", "验证码错误");
			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);;
			return;
		}
		
		//判断用户名密码
		
		//登录通过
		response.sendRedirect(request.getContextPath()+ "/backend/main.jsp");
		
	}

}
