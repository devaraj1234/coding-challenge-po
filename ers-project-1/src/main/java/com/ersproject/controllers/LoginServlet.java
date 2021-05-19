package com.ersproject.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.User;
import com.ersproject.service.CommonService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	CommonService commonService = new CommonService();
	static User user;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(!username.equals(null) && !password.equals(null)) {
			boolean validateLogin = commonService.validateLogin(username, password);
			if(validateLogin == true) {

				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				user = commonService.getUser(username, password);
				session.setAttribute("user", user);
				if(user.getUser_role_id().getUser_role().equalsIgnoreCase("employee")) {
					response.sendRedirect("employeeMainPage");
				}
				else if(user.getUser_role_id().getUser_role().equalsIgnoreCase("manager")) {
					// TODO
					response.sendRedirect("");
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");	
				rd.include(request,response);
			}

		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
	}
}
