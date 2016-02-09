package com.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.estore.domain.User;
import com.estore.service.UserService;

public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService us = new UserService();
		User loginUser = us.checkUser(username, password);
		
		// wrong username or password, return to logIn page
		if(loginUser == null)
		{
			request.setAttribute("message", "wrong username or password.   Please login again");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else
		{
			// login Success
			// put an login user into session    loginUser value can be change to be a unique lick IP
			request.getSession().setAttribute("loginUser", loginUser);
			// redirect
			response.sendRedirect("/estore/index.jsp");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     doGet(request,response);
	}

}
