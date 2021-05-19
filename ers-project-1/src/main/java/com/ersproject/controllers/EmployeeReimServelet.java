package com.ersproject.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.Reimbursement;
import com.ersproject.model.User;
import com.ersproject.service.EmployeeService;
import com.google.gson.Gson;

@WebServlet("/employee-reim-page")
public class EmployeeReimServelet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		String serviceSelection = request.getParameter("selection-type");
		System.out.println(serviceSelection);
		
		if (serviceSelection.equals("Submit New Request")) {
			response.sendRedirect("reim-request-page");
		}

		else if (serviceSelection.equals("Pending Request")) {
			List<Reimbursement> pending_request = new ArrayList<>();
			List<Reimbursement> all_reim_request = empService.getReimbursementDetails(user.getUser_id());
			for (Reimbursement reim : all_reim_request) {
				if (reim.getReimb_status_id().getReimb_status().equalsIgnoreCase("pending")) {
					pending_request.add(reim);
				}
			}
			for(Reimbursement reim : pending_request) {
				System.out.println(reim);
			}
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
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//new Gson().toJson(resolved_request, response.getWriter());
		}

		else if (serviceSelection.equals("All Request")) {

			List<Reimbursement> all_reim_request = empService.getReimbursementDetails(user.getUser_id());
			for(Reimbursement reim : all_reim_request) {
				System.out.println(reim);
			}
			
			
			
			// response.setContentType("application/json");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(all_reim_request.toString());
			// new Gson().toJson(all_reim_request, response.getWriter());

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

	}
}