package com.shopapi.revature.dao;

import com.shopapi.revature.model.Customer;

public interface CustomerDAO extends GenericDAO<Customer>{
	
	public Customer getByLoginId(int loginId);

}
