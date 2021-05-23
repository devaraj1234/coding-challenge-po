package com.ersproject.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ersproject.dao.ReimDAOImpl;
import com.ersproject.dao.UserDAOImpl;
import com.ersproject.model.Reimbursement;
import com.ersproject.model.User;

public class ManagerService {
	
	UserDAOImpl userDAO = new UserDAOImpl();
	ReimDAOImpl reimDAO = new ReimDAOImpl();
	
	public List<User> listAllEmployees(){
		return userDAO.listAllemployee();
	}
	
	public List<Reimbursement> pendingReimbursementList(){
		
		List<Reimbursement> pendingReimList = new ArrayList<>();
		List<Reimbursement> allReimList = reimDAO.allReimbursementRequest();
		for(Reimbursement reim: allReimList) {
			if (reim.getReimb_status_id().getReimb_status().equalsIgnoreCase("pending")) {
				pendingReimList.add(reim);
			}
		}
		return pendingReimList;
	}
	
	public List<Reimbursement> resolvedReimbursementList(){
		return reimDAO.getResolvedReimbursement();
	}
	
	public List<Reimbursement> viewIndividualReimRequest(String fname){
		return reimDAO.viewIndividualReimRequest(fname);
	}

	public boolean acceptReimbursementRequest(int request_id, String action, Date resolvedDate, int resolverId) {
		return reimDAO.approveReimbursement(request_id, action, resolvedDate, resolverId);
	}

	public boolean rejectReimbursementRequest(int request_id, String action, Date resolvedDate, int resolverId) {
		return reimDAO.rejectReimbursement(request_id, action, resolvedDate, resolverId);
	}

}
