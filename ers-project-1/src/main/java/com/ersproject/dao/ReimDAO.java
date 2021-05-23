package com.ersproject.dao;

import java.sql.Date;
import java.util.List;

import com.ersproject.model.Reimbursement;

public interface ReimDAO {

	public boolean submitReimbursementRequest(Reimbursement newRequest);

	public List<Reimbursement> viewReimbursementList(int user_id);

	public int getReimbursementTypeId(String reim_type);

	public boolean approveReimbursement(int request_id, String action, Date resolvedDate, int resolverId);

	public boolean rejectReimbursement(int request_id, String action, Date resolvedDate, int resolverId);

	public List<Reimbursement> viewIndividualReimRequest(String first_name);

	public List<Reimbursement> allReimbursementRequest();
	
	public List<Reimbursement> getResolvedReimbursement();
}
