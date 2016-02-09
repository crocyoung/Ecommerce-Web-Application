package com.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.estore.domain.User;
import com.estore.service.UserService;

public class RegistServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
/********* verify the checkcode****************************************************************/
		
		String codeInSession = (String)request.getSession().getAttribute("checkcode_session");
		String inputCode = (String)request.getParameter("checkcode");
		
		// ignore the uppercase and lowercase
		if(inputCode == null || !inputCode.equalsIgnoreCase(codeInSession) )
		{
			// dispatcher
			request.setAttribute("message", "wrong code");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
/********* verify the checkcode****************************************************************/
		
/********* encapsulate data into bean***********************************************************/

		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 		
/********* encapsulate data into bean***********************************************************/

/********* call service level to process Bean***********************************************************/
		
		// feedback = 1: existed username  2: exised email  3: register successfully
		UserService us = new UserService(); 
		int feedback = us.regist(user);
		
		switch(feedback)
		{
		case 1:
			// username is taken already 
			request.setAttribute("message", "Sorry£¬this username is already taken. Please pick another one");
			// dispatcher
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			break;
			
		case 2:
			//email address is taken already 
			request.setAttribute("message", "Sorry£¬this email address is already taken. Please pick another one");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			break;
			
		case 3:
			// registered successfully, turn to a new jsp page
			// put this user's info into session, then achieve auto login
			User loginUser = user;
			request.getSession().setAttribute("loginUser", loginUser);
			request.setAttribute("message", "Congratulations, registration successful");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
/********* call service level to process Bean***********************************************************/		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		       
		doGet(request, response);
	}
}
