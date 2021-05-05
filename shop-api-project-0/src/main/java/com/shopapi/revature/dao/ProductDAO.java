package com.shopapi.revature.dao;

import java.util.List;

import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.Sales;

public interface ProductDAO extends GenericDAO<Product> {
	
	public List<Sales> viewAllProductOwned(Sales owner);
	public List<Sales> viewSalesHistory();
}
