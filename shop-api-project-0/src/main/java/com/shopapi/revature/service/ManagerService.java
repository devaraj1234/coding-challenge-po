package com.shopapi.revature.service;

import com.shopapi.revature.dao.EmployeeDAOImpl;
import com.shopapi.revature.model.Employee;

public class ManagerService {
	EmployeeDAOImpl empDAO = new EmployeeDAOImpl();
	
	public boolean addEmployee (Employee employee) {
		return empDAO.addEmployee(employee);
	}
	

}
