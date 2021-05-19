package com.ersproject.dao;

import java.util.List;

import com.ersproject.model.User;

public interface UserDAO {

	public boolean validateLogin(String userName, String password);

	public User viewPersonalInfo(String userName, String password);

	public boolean updatePersonalInfo(User user);

	public List<User> listAllemployee();
}
