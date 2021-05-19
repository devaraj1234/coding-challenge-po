package com.ersproject.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/employee-main-page")
public class EmoplyeeMainPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	    response.setContentType("text/html");  
		String serviceSelection = request.getParameter("selection-type");
		System.out.println(serviceSelection);
		if (serviceSelection.equals("Emp Reimbursement")) {
			response.sendRedirect("employee-reim-page.html");
		}

		else if (serviceSelection.equals("Personal Information")) {
			response.sendRedirect("employee-info-page.html");
		}

		else if (serviceSelection.equals("Log Out")) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.invalidate();
			response.sendRedirect("login");
		}

		else {
			response.sendError(404, "Path not supported");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//request.getRequestDispatcher("employee-main-page.html").forward(request, response);
	}
}