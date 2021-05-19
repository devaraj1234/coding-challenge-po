package com.ersproject.controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.User;
import com.ersproject.service.EmployeeService;
import com.ersproject.utility.ServletUtility;

@WebServlet("/reim-request-page")
public class ReimRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	EmployeeService empService = new EmployeeService();
	ServletUtility servletUtility = new ServletUtility();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("reim-request-page.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Date reim_date = servletUtility.convertStringToDate(request.getParameter("reim_date"));
			String reim_type = request.getParameter("reim_type");
			Double reim_amount = servletUtility.convertStringToDouble(request.getParameter("reim-amount"));
			String reim_description = request.getParameter("reim_description");
			// TODO upload image
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			if (!reim_date.equals(null) && !reim_type.equals(null) && !reim_amount.equals(null)) {
				boolean isUpdated = empService.submitNewReimbursementRequest(reim_date, reim_type, reim_amount,
						reim_description, user);
				if (isUpdated == true) {
					// TODO Write success message to browser
					request.getRequestDispatcher("employee-reim-page.html").forward(request, response);
				} else {
					// TODO write failed message to broswer
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
