package com.sina.cms.backend.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.cms.model.UserModel;
import com.sina.cms.utils.StringUtils;

@WebServlet("/backend/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		
		String sessionCheckcode = (String) request.getSession().getAttribute("checkcode");
		
//		//验证码不正确
//		if (sessionCheckcode == null || checkcode == null || !sessionCheckcode.equalsIgnoreCase(checkcode)) {
//			redirect("验证码错误", request, response);
//			return;
//		}
		
		if (StringUtils.isEmpty(username)) {
			redirect("请输入用户名", request, response);
			return ;
		}
		
		if (StringUtils.isEmpty(password)) {
			redirect("请输入密码", request, response);
			return ;
		}
		
		//判断用户名密码  
		UserModel user = new UserModel();
		user.setUsername("admin");
		user.setPassword("admin");
		 
		//登录通过
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+ "/backend/main.jsp");
	}
	
	private void redirect(String msg, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		request.setAttribute("error", "验证码错误");
//		request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
		
		request.getSession().setAttribute("error", msg);
		response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
	}

}
