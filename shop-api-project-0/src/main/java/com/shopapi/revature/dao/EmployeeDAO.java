package com.shopapi.revature.dao;

import com.shopapi.revature.model.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	public boolean removeEmployee(Employee employee);

}
