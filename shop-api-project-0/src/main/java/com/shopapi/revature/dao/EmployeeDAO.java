package com.shopapi.revature.dao;

import com.shopapi.revature.model.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	public boolean removeEmployee(int employee_id);

}
