package com.shopapi.revature.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.User;
import com.shopapi.revature.service.CommonService;

public class CommonServiceTest {

		CommonService commonService = new CommonService();
		
		@Test
		public void getUserIdTest() {
			int expected_id = 1;
			int actual_id = commonService.getUserId("manager");
			assertEquals(expected_id, actual_id);
		}
		
		@Test
		public void createLoginTest() {
			
		}
		
		@Test
		public void validateUserLoginTest() {
			boolean expected_result = false;
			boolean actual_result = commonService.validateUserLogin(new LoginDetails(null, "abc", "abc", new User()));
			assertEquals(expected_result, actual_result);
		}
		
		@Test
		public void getLoginDetailsTest() {
			assertTrue(commonService.getLoginDetails() instanceof LoginDetails);
			
		}
}
