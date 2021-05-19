package com.ersproject.dao;

import java.util.List;

import com.ersproject.model.Reimbursement;
import com.ersproject.model.User;

public interface EmployeeDAO {
	
	public boolean validateLogin(String userName, String password);
	public boolean submitReimbursementRequest(Reimbursement newRequest);
	public List<Reimbursement> viewPendingRequest();
	public List<Reimbursement> viewResolvedRequest();
	public List<Reimbursement> viewAllRequest();
	public User viewPersonalInfo(String userName, String password);
	public boolean updatePersonalInfo(String userName, String password);

}
