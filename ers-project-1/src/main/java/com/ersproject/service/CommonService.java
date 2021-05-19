package com.ersproject.service;

import com.ersproject.dao.EmployeeDAO;
import com.ersproject.dao.EmployeeDAOImpl;
import com.ersproject.model.User;

public class CommonService {
	
	EmployeeDAO edao = new EmployeeDAOImpl();
	
	public boolean validateLogin(String usernmae, String password) {
		return edao.validateLogin(usernmae, password);
	}

	public User getUser(String username, String password) {
		return edao.viewPersonalInfo(username, password);
	}
	
	

}
