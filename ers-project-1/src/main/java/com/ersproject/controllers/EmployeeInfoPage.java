package com.ersproject.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.User;
import com.ersproject.service.CommonService;
import com.ersproject.service.EmployeeService;
import com.ersproject.utility.ServletUtility;
import com.google.gson.Gson;

@WebServlet("/employee-info-page")
public class EmployeeInfoPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletUtility servletUtil = new ServletUtility();
	EmployeeService empService = new EmployeeService();
	CommonService commonService = new CommonService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceSelection = request.getParameter("selection-type");
		if (serviceSelection.equals("View Information")) {
			HttpSession session = request.getSession(false);
			User sessionUser = (User) session.getAttribute("user");
			User user = commonService.getUser(sessionUser.getUser_name(), sessionUser.getUser_password());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(user));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String new_first_name = request.getParameter("first-name");
		String new_last_name = request.getParameter("last-name");
		String new_email = request.getParameter("email");

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		User updated_user = servletUtil.getUpdatedUser(new_first_name, new_last_name, new_email, user);

		if (empService.updateUserInformation(updated_user)) {
			request.getRequestDispatcher("employee-info-page.html").forward(request, response);
		} else {
			request.getRequestDispatcher("employee-info-page.html").forward(request, response);
		}

	}

}
