package com.ersproject.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ersproject.model.User;
import com.ersproject.service.ManagerService;
import com.google.gson.Gson;

@WebServlet("/manager-home-page/*")
public class ManagerHomePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ManagerService managerService = new ManagerService();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		HttpSession session = request.getSession(false);
		
	    String serviceSelection = request.getParameter("selection-type");
	    
	    if (serviceSelection.equals("Employee Information")) {
			
	    	List<User> allEmployees = managerService.listAllEmployees();
	    	response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(allEmployees));
	    
	    }
	    
	    else if(serviceSelection.equals("Reimbursement Request")) {
	    	response.sendRedirect("reimburs-manage-page.html");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
