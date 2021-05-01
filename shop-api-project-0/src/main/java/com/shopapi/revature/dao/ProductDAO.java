package com.shopapi.revature.dao;

import java.util.List;

import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.ProductOwner;

public interface ProductDAO extends GenericDAO<Product> {
	
	public List<ProductOwner> viewAllProductOwned(ProductOwner owner);
	
}
