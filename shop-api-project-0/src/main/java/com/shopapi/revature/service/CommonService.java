package com.shopapi.revature.service;

import java.util.List;

import com.shopapi.revature.dao.LoginDetailsDAOImpl;
import com.shopapi.revature.dao.UserDAOImpl;
import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.User;

public class CommonService {

	UserDAOImpl userDAO = new UserDAOImpl();
	LoginDetailsDAOImpl loginDAO = new LoginDetailsDAOImpl();

	public int getUserId(String user) {
		int user_id = 0;
		List<User> users = userDAO.getAll();
		for (User usr : users) {
			if (usr.getUser_role().equalsIgnoreCase(user)) {
				user_id = usr.getUser_id();
			}
		}
		return user_id;
	}

	public void createLogin(LoginDetails loginDetails) {
		loginDAO.add(loginDetails);
	}

	public boolean validateUserLogin(LoginDetails login){
			return loginDAO.validateLogin(login);
	}
	
	public LoginDetails getLoginDetails() {
		return loginDAO.getLoginDetail();
		
	}
}
