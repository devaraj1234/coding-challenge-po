package com.ersproject.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.Reimbursement;
import com.ersproject.model.User;
import com.ersproject.service.EmployeeService;
import com.ersproject.utility.ServletUtility;
import com.google.gson.Gson;

@WebServlet("/employee-reim-page")
public class EmployeeReimServelet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();
	ServletUtility servletUtility = new ServletUtility();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		String serviceSelection = request.getParameter("selection-type");

		if (serviceSelection.equals("Submit New Request")) {
			response.sendRedirect("reim-request-page.html");
		}

		else if (serviceSelection.equals("Pending Request")) {
			List<Reimbursement> pending_request = new ArrayList<>();
			List<Reimbursement> all_reim_request = empService.getReimbursementDetails(user.getUser_id());
			for (Reimbursement reim : all_reim_request) {
				if (reim.getReimb_status_id().getReimb_status().equalsIgnoreCase("pending")) {
					pending_request.add(reim);
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(pending_request));
		}

		else if (serviceSelection.equals("Resolved Request")) {
			List<Reimbursement> resolved_request = new ArrayList<>();
			List<Reimbursement> all_reim_request = empService.getReimbursementDetails(user.getUser_id());
			for (Reimbursement reim : all_reim_request) {
				if (reim.getReimb_status_id().getReimb_status().equalsIgnoreCase("rejected")
						|| reim.getReimb_status_id().getReimb_status().equalsIgnoreCase("approved")) {
					resolved_request.add(reim);
				}
			}
			response.setContentType("application/json;charset=UTF-8");
			ServletOutputStream jsonOut = response.getOutputStream();
			String output = new Gson().toJson(resolved_request);
			jsonOut.print(output);
		}

		else if (serviceSelection.equals("All Request")) {

			List<Reimbursement> all_reim_request = empService.getReimbursementDetails(user.getUser_id());
			response.setContentType("application/json;charset=UTF-8");
			ServletOutputStream jsonOut = response.getOutputStream();
			String output = new Gson().toJson(all_reim_request);
			jsonOut.print(output);
		}

		else if (serviceSelection.equals("Log Out")) {
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
		String selection_type = request.getParameter("submit/cancel");
		if (selection_type.equalsIgnoreCase("Submit")) {

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
						request.getRequestDispatcher("employee-reim-page.html").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selection_type.equalsIgnoreCase("Cancel")) {
			request.getRequestDispatcher("employee-reim-page.html").forward(request, response);

		}

	}
}