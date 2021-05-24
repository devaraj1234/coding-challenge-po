package com.ersproject.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ersproject.dao.ReimDAOImpl;
import com.ersproject.model.Reimbursement;
import com.ersproject.model.ReimbursementStatus;
import com.ersproject.model.ReimbursementType;
import com.ersproject.model.User;
import com.ersproject.model.UserRoles;
import com.ersproject.service.EmployeeService;

public class EmployeeServiceTest {

	static Reimbursement reim;
	static User user, user1, user2;

	@Mock
	static EmployeeService empService;
	@Mock
	static ReimDAOImpl reimDao;

	@BeforeClass
	public static void setup() {
		reim = new Reimbursement();
		reimDao = Mockito.mock(ReimDAOImpl.class);
		empService = Mockito.mock(EmployeeService.class);

		UserRoles userrole1 = new UserRoles(1, "manager");
		UserRoles userrole2 = new UserRoles(1, "employee");

		ReimbursementStatus reimStatus = new ReimbursementStatus(1, "pending");
		ReimbursementStatus reimStatus1 = new ReimbursementStatus(2, "approved");
		ReimbursementStatus reimStatus3 = new ReimbursementStatus(3, "rejected");

		ReimbursementType reimType = new ReimbursementType(1, "fooding");
		ReimbursementType reimType2 = new ReimbursementType(2, "lodging");

		user = new User(1, "dev", "password", "Dev", "Acharya", "dev.acharya@gmail.com", userrole2);
		user1 = new User(2, "devaraj", "password", "Devaraj", "Acharya", "dev.acharya287@gmail.com", userrole1);
		user2 = new User(3, "durga", "password", "Durga", "Paudel", "dev.acharya287@gmail.com", userrole2);

		
		Reimbursement reim = new Reimbursement(1, 200.00, null, null, "This is fooding exp", null, user, null,
				reimStatus, reimType);
		Reimbursement reim2 = new Reimbursement(2, 3000.00, null, null, "This is lodging exp", null, user, user1,
				reimStatus1, reimType2);
		Reimbursement reim3 = new Reimbursement(3, 8000.00, null, null, "This is lodging exp", null, user2, null,
				reimStatus3, reimType2);

		List<Reimbursement> reimList = new ArrayList<>();
		reimList.add(reim);
		reimList.add(reim2);
		reimList.add(reim3);

		Mockito.when(reimDao.viewReimbursementList(user.getUser_id())).thenReturn(reimList);
		Mockito.when(reimDao.getReimbursementTypeId(reimType.getReim_type())).thenReturn(reimType.getReim_type_id());
		Mockito.when(reimDao.getReimbursementTypeId(reimType2.getReim_type())).thenReturn(reimType2.getReim_type_id());
		Mockito.when(reimDao.approveReimbursement(0, null, null, 0)).thenReturn(true);
		Mockito.when(reimDao.rejectReimbursement(0, null, null, 0)).thenReturn(true);
		Mockito.when(reimDao.allReimbursementRequest()).thenReturn(reimList);

	}

	@Test
	public void testSubmitNewRequest() {
		String Stringdate = "2021-05-14";
		Date date = Date.valueOf(Stringdate);
		assertEquals(false,
				empService.submitNewReimbursementRequest(null, "Fooding", 100.00, "fooding expenses", user));
		assertEquals(false,
				empService.submitNewReimbursementRequest(date, "Fooding", 100.00, "fooding expenses", user));

	}

	@Test
	public void submitReimbursementRequest1() {
		Mockito.when(reimDao.submitReimbursementRequest(reim)).thenReturn(true);
		assertTrue(reimDao.submitReimbursementRequest(reim));
	}

	@Test
	public void submitReimbursementRequest3() {
		Mockito.when(reimDao.submitReimbursementRequest(reim)).thenReturn(true);
		assertTrue(reimDao.submitReimbursementRequest(reim));
	}

	@Test
	public void submitReimbursementRequest2() {
		Mockito.when(reimDao.submitReimbursementRequest(reim)).thenReturn(true);
		assertFalse(reimDao.submitReimbursementRequest(null));
	}

	@Test
	public void viewreimbursementListById() {
		List<Reimbursement> reimList = reimDao.viewReimbursementList(6);
		assertEquals(0, reimList.size());
		List<Reimbursement> reimList1 = reimDao.viewReimbursementList(1);
		List<Reimbursement> reimListUser1 = new ArrayList<>();
		for(Reimbursement reimbur : reimList1) {
			if(reimbur.getReimb_author().getUser_id() == 1) {
				reimListUser1.add(reimbur);
			}
		}
		assertEquals(2, reimListUser1.size());
	}
	
	@Test
	public void getReimbursementTypeIdTest() {
		assertEquals(1, reimDao.getReimbursementTypeId("fooding"));
		assertEquals(2, reimDao.getReimbursementTypeId("lodging"));

	}
	
	@Test
	public void approveReimbursementTest() {
		assertEquals(true, reimDao.approveReimbursement(0, null, null, 0));
	}
	
	@Test
	public void rejectReimbursementTest() {
		assertEquals(true, reimDao.rejectReimbursement(0, null, null, 0));

	}
	@Test
	public void allReimbursementRequestTest() {
		assertEquals(3, reimDao.allReimbursementRequest().size());
	}

}
