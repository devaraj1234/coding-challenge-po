package com.ersproject.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("reimbursementRequestPage.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String date = request.getParameter("reim_date");
		String reim_type = request.getParameter("reim_type");
		String reim_amount = request.getParameter("reim_amount");
		String reim_description = request.getParameter("reim_description");
		//Blob reim_receipt = request.getp
		
		if(!date.equals(null) && !reim_type.equals(null) & !reim_amount.equals(null)) {
			
		}

	}

}
