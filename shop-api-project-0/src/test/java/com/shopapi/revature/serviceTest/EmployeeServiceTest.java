package com.shopapi.revature.serviceTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.shopapi.revature.model.Product;
import com.shopapi.revature.service.EmployeeService;

public class EmployeeServiceTest {

	EmployeeService empService = new EmployeeService();

	@Test(expected = NullPointerException.class)
	public void addProductToListTest() {
		empService.addProductToList(null);
	}

	@Test
	public void getAllProductTest() {
		assertTrue(empService.getAllProducts().size() >= 0);
	}

	@Test(expected = NullPointerException.class)
	public void deleteProductTest() {
		empService.deleteProduct(new Product());
	}

	@Test
	public void getAllOfferMadeTest() {
		assertTrue(empService.getAllOfferMade().size() >= 0);
	}
	
	@Test
	public void viewAllPaymentTest() {
		assertTrue(empService.viewAllPaymentMade().size() >= 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void acceptOfferTest() {
		empService.acceptOffer(null);
	}
}
