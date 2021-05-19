package com.ersproject.service;

import com.ersproject.dao.UserDAO;
import com.ersproject.dao.UserDAOImpl;
import com.ersproject.model.User;

public class CommonService {
	
	UserDAO edao = new UserDAOImpl();
	
	public boolean validateLogin(String usernmae, String password) {
		return edao.validateLogin(usernmae, password);
	}

	public User getUser(String username, String password) {
		return edao.viewPersonalInfo(username, password);
	}
	
	

}
