package com.ersproject.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.User;
import com.ersproject.service.CommonService;
@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	CommonService commonService = new CommonService();
	static User user;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	    response.setContentType("text/html");  

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(!username.equals(null) && !password.equals(null)) {
			boolean validateLogin = commonService.validateLogin(username, password);
			if(validateLogin == true) {
				
				user = commonService.getUser(username, password);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				//response.addCookie((Cookie) session);
				if(user.getUser_role_id().getUser_role().equalsIgnoreCase("employee")) {
					response.sendRedirect("employee-main-page.html");
				}
				else if(user.getUser_role_id().getUser_role().equalsIgnoreCase("manager")) {
					// TODO
					response.sendRedirect("");
				}
			}
			else {			
				PrintWriter out= response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}

		}else {
			request.getRequestDispatcher("/login.html").forward(request, response);;
		}
	}
}
