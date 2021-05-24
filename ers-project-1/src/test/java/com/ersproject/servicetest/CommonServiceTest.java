package com.ersproject.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ersproject.model.User;
import com.ersproject.model.UserRoles;
import com.ersproject.service.CommonService;


public class CommonServiceTest {	
	
	@Mock
	static CommonService commonService;
	
	@BeforeClass
	public static void setup() {
		commonService = Mockito.mock(CommonService.class);
		
		List<User> userList = new ArrayList<>();
		User user = new User(1, "dev", "password", "Dev", "Acharya", "dev.acharya@gmail.com", new UserRoles() );
		User user1 = new User(2, "devaraj", "password", "Devaraj", "Acharya", "dev.acharya287@gmail.com", new UserRoles());
		userList.add(user1);
		userList.add(user);
		
		Mockito.when(commonService.getUser(user.getUser_name(), user.getUser_password())).thenReturn(user);
		Mockito.when(commonService.getUser(user1.getUser_name(), user1.getUser_password())).thenReturn(user1);
		Mockito.when(commonService.validateLogin(user1.getUser_name(), user1.getUser_password())).thenReturn(true);
		Mockito.when(commonService.validateLogin(user.getUser_name(), user.getUser_password())).thenReturn(true);
		
	}
	
	@Test
	public void validateUserLoginTest() {
		assertEquals(true, commonService.validateLogin("devaraj", "password"));
		assertEquals(true, commonService.validateLogin("dev", "password"));
	}
	
	@Test
	public void validateUserLoginTest1() {
		assertEquals(false, commonService.validateLogin("devaraj", "pas"));
		assertEquals(false, commonService.validateLogin("abc", "password"));
	}
	
	@Test
	public void getUserTest() {
		assertNotNull(commonService.getUser("dev", "password"));
		assertNotNull(commonService.getUser("devaraj", "password"));	
		assertNull(commonService.getUser("abc", "abc"));
	}
	
	@Test
	public void getUserTest1() {
		User user = commonService.getUser("dev", "password");
		assertEquals("dev", user.getUser_name());
		assertEquals("Dev", user.getUser_first_name());
		assertEquals("Acharya", user.getUser_last_name());
	}
	
	
}
