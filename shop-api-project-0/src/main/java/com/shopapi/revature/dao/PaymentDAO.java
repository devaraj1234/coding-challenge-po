package com.shopapi.revature.dao;

import java.util.List;

import com.shopapi.revature.model.AccountCollection;

public interface PaymentDAO {
	
	public List<AccountCollection> viewAllPayment();
	public List<AccountCollection> viewAllPaymentForCustomer(AccountCollection customer);
	public AccountCollection viewRemainingPayment(int order_no);
	public boolean makePayment(int order_no, double amount);
}
