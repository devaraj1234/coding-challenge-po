package com.shopapi.revature.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.ProductOwner;
import com.shopapi.revature.service.CustomerService;

public class CustomerServiceTest {
	
	CustomerService customerService = new CustomerService();
	
	@Test
	public void getCustomerIdTest() {
		int expected_id = 1;
		int actual_id = customerService.getCustomerId(4);
		assertEquals(expected_id, actual_id);
	}
	
	@Test (expected=NullPointerException.class)
	public void addCustomerTest() {
		customerService.addCustomer(new Customer());
	}
	
	@Test
	public void getAllProductTest() {
		assertTrue(customerService.getAllProducts().size()>0);
	}
	
	@Test(expected=NullPointerException.class)
	public void offerMadeForProductTest() {
		customerService.offerMadeForProduct(null);
	}
	
	@Test
	public void viewAllProductOwnedTest() {
		assertTrue(customerService.viewAllProductOwned(new ProductOwner(null, new Customer(1))).size()>0);
	}
	
	@Test(expected=NullPointerException.class)
	public void viewAllPaymentListTest() {
		customerService.viewAllPaymentList(null);
	}
	
	@Test
	public void remainingPaymentTest() {
		assertTrue(customerService.remainingPayment(2) instanceof AccountCollection);
	}
	
	@Test
	public void makePaymentTest() {
		boolean expected_result = true;
		boolean actual_result = customerService.makePayment(0, 0);
		assertEquals(expected_result, actual_result);
	}
}
