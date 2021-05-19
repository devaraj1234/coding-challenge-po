package com.ersproject.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeReimbursementServelet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		request.getRequestDispatcher("employeeReimbursementPage.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String serviceSelection = request.getParameter("selection-type");
		
		if (serviceSelection.equals("Submit New Request")) {
			response.sendRedirect("reimbursement-request-page");
		}

		else if (serviceSelection.equals("Penidng Request")) {
			// TODO
		}
		
		else if (serviceSelection.equals("Resolved Request")) {
			// TODO
		}
		
		else if (serviceSelection.equals("All Request")) {
			// TODO
		}
		
		else if (serviceSelection.equals("Log Out")) {
			response.sendRedirect("login");
		}

		else {
			response.sendError(404, "Path not supported");
		}

		
	}

}