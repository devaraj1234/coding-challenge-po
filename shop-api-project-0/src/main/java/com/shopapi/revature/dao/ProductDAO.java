package com.shopapi.revature.dao;

import java.util.List;

import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.ProductOwned;

public interface ProductDAO extends GenericDAO<Product> {
	
	public List<ProductOwned> viewAllProductOwned(ProductOwned owner);
	
}
