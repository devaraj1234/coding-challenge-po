package com.shopapi.revature.service;

import java.util.List;

import com.shopapi.revature.dao.CustomerDAOImpl;
import com.shopapi.revature.dao.OfferedMadeDAOImpl;
import com.shopapi.revature.dao.PaymentDAOImpl;
import com.shopapi.revature.dao.ProductDAOImpl;
import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.ProductOwner;

public class CustomerService {

	CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	ProductDAOImpl productDAO = new ProductDAOImpl();
	OfferedMadeDAOImpl offerDAO = new OfferedMadeDAOImpl();
	PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

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
	
	public List<ProductOwner> viewAllProductOwned(ProductOwner owner){
		return productDAO.viewAllProductOwned(owner);
	}
	
	public List<AccountCollection> viewAllPaymentList(AccountCollection customer){
		return paymentDAO.viewAllPaymentForCustomer(customer);
	}
	
	public AccountCollection remainingPayment(int order_no) {
		return paymentDAO.viewRemainingPayment(order_no);
	}
	
	public boolean makePayment(int order_no, double amount) {
		return paymentDAO.makePayment(order_no, amount);
	}

}
