package com.ersproject.dao;

import java.util.List;

import com.ersproject.model.Reimbursement;

public interface ReimDAO {

	public boolean submitReimbursementRequest(Reimbursement newRequest);

	public List<Reimbursement> viewReimbursementList(int user_id);

	public int getReimbursementTypeId(String reim_type);

	public boolean approveReimbursement(int author_id);

	public boolean rejectReimbursement(int author_id);

	public Reimbursement viewIndividualReimbursement(String first_name, String last_name);

	public List<Reimbursement> viewAllReimbursementRequest();
}
