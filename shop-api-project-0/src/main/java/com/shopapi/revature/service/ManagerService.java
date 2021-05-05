package com.shopapi.revature.service;

import java.util.List;

import com.shopapi.revature.dao.EmployeeDAOImpl;
import com.shopapi.revature.dao.ProductDAOImpl;
import com.shopapi.revature.model.Employee;
import com.shopapi.revature.model.Sales;

public class ManagerService {
	EmployeeDAOImpl empDAO = new EmployeeDAOImpl();
	ProductDAOImpl productDAO = new ProductDAOImpl();
	
	public boolean addEmployee (Employee employee) {
		return empDAO.addEmployee(employee);
	}
	
	public boolean removeEmployee(int employee_id) {
		return empDAO.removeEmployee(employee_id);
	}
	
	public List<Sales> viewSalesHistory(){
		return productDAO.viewSalesHistory();
	}

}
