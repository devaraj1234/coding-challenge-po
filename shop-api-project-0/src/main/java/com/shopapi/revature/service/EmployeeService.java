package com.shopapi.revature.service;

import java.util.List;

import com.shopapi.revature.dao.OfferedMadeDAOImpl;
import com.shopapi.revature.dao.PaymentDAOImpl;
import com.shopapi.revature.dao.ProductDAOImpl;
import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;

public class EmployeeService {
	
	ProductDAOImpl productDAO = new ProductDAOImpl();
	OfferedMadeDAOImpl offerMadeDAO = new OfferedMadeDAOImpl();
	PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
	
	public boolean addProductToList(Product product) {
		return productDAO.add(product);
	}
	
	public List<Product> getAllProducts() {
		return productDAO.getAll();
	}
	
	public boolean deleteProduct(Product product_id) {
		return productDAO.delete(product_id);
	}
	
	public List<OfferedMade> getAllOfferMade(){
		return offerMadeDAO.getAll();
	}
	
	public boolean acceptOffer(OfferedMade offer) {
		return offerMadeDAO.acceptOffer(offer);
	}
	
	public List<AccountCollection> viewAllPaymentMade(){
		return paymentDAO.viewAllPayment();
	}

}
