package com.shopapi.revature.service;

import java.util.List;

import com.shopapi.revature.dao.CustomerDAOImpl;
import com.shopapi.revature.dao.OfferedMadeDAOImpl;
import com.shopapi.revature.dao.ProductDAOImpl;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.ProductOwned;

public class CustomerService {

	CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	ProductDAOImpl productDAO = new ProductDAOImpl();
	OfferedMadeDAOImpl offerDAO = new OfferedMadeDAOImpl();

	public int getCustomerId(int loginID) {
		if (customerDAO.getByLoginId(loginID).getCustomer_id() != null) {
			return customerDAO.getByLoginId(loginID).getCustomer_id();
		} else
			return 0;
	}

	public boolean addCustomer(Customer customer) {
		return customerDAO.add(customer);
	}

	public List<Product> getAllProducts() {
		return productDAO.getAll();
	}

	public boolean offerMadeForProduct(OfferedMade offer) {
		return offerDAO.add(offer);
	}
	
	public List<ProductOwned> viewAllProductOwned(ProductOwned owner){
		return productDAO.viewAllProductOwned(owner);
	}

}
