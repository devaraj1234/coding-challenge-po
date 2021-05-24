package com.ersproject.service;

import java.sql.Date;
import java.util.List;

import com.ersproject.dao.ReimDAO;
import com.ersproject.dao.ReimDAOImpl;
import com.ersproject.dao.UserDAO;
import com.ersproject.dao.UserDAOImpl;
import com.ersproject.model.Reimbursement;
import com.ersproject.model.ReimbursementType;
import com.ersproject.model.User;

public class EmployeeService {

	UserDAO userDao = new UserDAOImpl();
	ReimDAO reimDao = new ReimDAOImpl();

	public boolean submitNewReimbursementRequest(Date expense_date, String reim_type, double expense_amount,
			String reim_des, User user) {

		Reimbursement reim_request = null;
		int reim_type_id = reimDao.getReimbursementTypeId(reim_type);

		if (reim_type_id > 0) {
			reim_request = new Reimbursement(null, expense_amount, expense_date, null, reim_des, null, user, new User(),
					null, new ReimbursementType(reim_type_id, null));
		}
		return reimDao.submitReimbursementRequest(reim_request);
	}
	
	public List<Reimbursement> getReimbursementDetails(int user_id){
		return reimDao.viewReimbursementList(user_id);
	}

	public boolean  updateUserInformation(User updated_user) {
		return userDao.updatePersonalInfo(updated_user);
		
	}

}
