package com.shopapi.revature.dao;

import com.shopapi.revature.model.LoginDetails;

public interface LoginDetailsDAO extends GenericDAO<LoginDetails> {
	
	public boolean validateLogin(LoginDetails login);
	public LoginDetails getLoginDetail();

}
