package com.ersproject.controllers;

import java.io.IOException;
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
import com.ersproject.service.ManagerService;
import com.google.gson.Gson;

@WebServlet("/reimburse-manage-page/*")
public class ReimburseManagePage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ManagerService managerService = new ManagerService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);

		String serviceSelection = request.getParameter("selection-type");
		if (serviceSelection != null) {
			if (serviceSelection.equals("View Resolved Request")) {
				List<Reimbursement> resolvedReimLsit = managerService.resolvedReimbursementList();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(new Gson().toJson(resolvedReimLsit));
			}

			else if (serviceSelection.equals("View Pending Request")) {
				List<Reimbursement> pendingReimList = managerService.pendingReimbursementList();
				response.setContentType("application/json;charset=UTF-8");
				ServletOutputStream jsonOut = response.getOutputStream();
				String output = new Gson().toJson(pendingReimList);
				jsonOut.print(output);
			}

			else if (serviceSelection.equals("Log Out")) {
				session.removeAttribute("user");
				session.invalidate();
				response.sendRedirect("login");
			}

			else if (serviceSelection.equals("Back")) {
				response.sendRedirect("manager-home-page.html");
			}

			else {
				response.sendError(404, "Path not supported");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String serviceSelection = request.getParameter("selection-type");
		if (serviceSelection != null) {
			if (serviceSelection.equals("Search")) {
				String userName = request.getParameter("user-name");
				List<Reimbursement> individualReimList = managerService.viewIndividualReimRequest(userName);

				response.setContentType("application/json;charset=UTF-8");
				ServletOutputStream jsonOut = response.getOutputStream();
				String output = new Gson().toJson(individualReimList);
				jsonOut.print(output);
			}

			else if (serviceSelection.equals("Confirm Action")) {

				int request_id = Integer.parseInt(request.getParameter("request_id"));
				String action = request.getParameter("action");

				long millis = System.currentTimeMillis();
				java.sql.Date resolvedDate = new java.sql.Date(millis);

				HttpSession session = request.getSession(false);
				User resolver = (User) session.getAttribute("user");
				int resolverId = resolver.getUser_id();

				if (action.equalsIgnoreCase("Accept")) {
					boolean acceptedChanged = managerService.acceptReimbursementRequest(request_id, action,
							resolvedDate, resolverId);
					response.sendRedirect("reimburs-manage-page.html");
					if (acceptedChanged == true) {
						// TODO Display success message to the browser
					}else {
						// display failure message
					}

				} else if (action.equalsIgnoreCase("Reject")) {
					boolean rejectedChanged = managerService.rejectReimbursementRequest(request_id, action,
							resolvedDate, resolverId);
					response.sendRedirect("reimburs-manage-page.html");
					if (rejectedChanged == true) {
						// TODO Display success message to the browser
					}else {
						// display failure message
					}

				}
			}
		}
	}
}
