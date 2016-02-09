package com.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   // delete the key of loginUser in Session. this method did not delete the sessionID 
		   // request.getSession().invalidate(); this method for delete whole session 
		      request.getSession().removeAttribute("loginUser");
		    
		   // redirect to main page
		      response.sendRedirect("/estore/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    doGet(request,response);
	}
}
