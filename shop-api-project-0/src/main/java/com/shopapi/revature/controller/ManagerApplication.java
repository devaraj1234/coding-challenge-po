package com.shopapi.revature.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.Exceptions.InvalidLoginException;
import com.shopapi.revature.model.Employee;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.Sales;
import com.shopapi.revature.model.User;
import com.shopapi.revature.service.CommonService;
import com.shopapi.revature.service.ManagerService;

public class ManagerApplication {

	private static Logger log = LogManager.getLogger(EmployeeApplication.class);

	public static void managerialActivities() {
		System.out.println("Welcome to Manager Portal of XYZ Auction Inc..!!!!!!!!!!!!!!!");
		CommonService commonService = new CommonService();
		ManagerService managerService = new ManagerService();
		final String user = "manager";
		Scanner scan = MainSimulator.scan;
		loop: while (true) {
			initialInstructions();
			String menu = scan.next();
			selection: switch (menu) {
			case "1": {
				try {
					log.info("user login function stared");
					System.out.println("Enter User Name: ");
					String userName = scan.next();
					System.out.println("Enter User Password: ");
					String userPassword = scan.next();

					boolean loginStaus = commonService
							.validateUserLogin(new LoginDetails(null, userName, userPassword, new User(null, user)));
					if (loginStaus == true) {
						LoginDetails login = commonService.getLoginDetails();
						System.out.println("--------------------------------------------------------------------");
						System.out.println("User: " + login.getLogin_user() + "\t\t\t\t\tUser_role: "
								+ login.getUser_role().getUser_role());
						System.out.println("---------------------------------------------------------------------");
						managerloop: while (true) {
							managerInstruction();
							String employeeMenu = scan.next();
							managerSelection: switch (employeeMenu) {
							case "1": {
								System.out.println("Enter Employee First Name: ");
								String fname = scan.next();
								System.out.println("Enter Employee Last Name: ");
								String lname = scan.next();
								System.out.println("Enter Employee Email: ");
								String email = scan.next();
								System.out.println("Enter Employee Position: ");
								String position = scan.next();
								System.out.println("Enter Login User Name for Employee: ");
								String loginUser = scan.next();
								System.out.println("Enter initial Login Password for Employee: ");
								String initialPassword = scan.next();
								final String emp_role = "employee";
								int userId = commonService.getUserId(emp_role);

								boolean addEmployee = managerService.addEmployee(new Employee(null, fname, lname, email,
										position,
										new LoginDetails(null, loginUser, initialPassword, new User(userId, null))));
								if (addEmployee == true) {
									System.out.println("successfully added a employee");
								} else {
									System.out.println("add employee to database failed");
								}

								break managerSelection;
							}
							case "2": {
								System.out.println("Enter employee id to remove Employee record");
								int employee_id = scan.nextInt();
								boolean removeEmployee = managerService.removeEmployee(employee_id);
								if (removeEmployee == true) {
									System.out.println("employee record removed successfully");
								} else {
									System.out.println("can not removed employee record");
								}

								break managerSelection;
							}
							case "3": {

								List<Sales> salesHistory = managerService.viewSalesHistory();
								for (Sales sale : salesHistory) {
									System.out.print("Order No: " + sale.getOrder_no() + "\tSales Date: "
											+ sale.getSales_date() + "\tProduct: " + sale.getProduct().getProduct_name()
											+ "\tQuantity: " + sale.getSales_quantity() + "\tPrice Per Unit: "
											+ sale.getPrice_Per_Unit() + "\tTotal Sale: "
											+ (sale.getPrice_Per_Unit() * sale.getSales_quantity()) + "\tCustomer: "
											+ sale.getCustomer().getCustomer_fname() + " "
											+ sale.getCustomer().getCustomer_lname() + "\n");
								}

								break managerSelection;
							}

							default:
								System.out.println("Wrong Input!!!!!!! Try Again");
								break managerSelection;
							case "q":
								System.out.println("Thank you for Using Employee Portal");
								break managerloop;
							}

						}

					} else {
						throw new InvalidLoginException("Invalid user name or password. Please try again!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break selection;
			}
			default:
				System.out.println("Wrong Input!!!!!!! Try Again");
				break selection;
			case "q":
				System.out.println("Thank you for Using Employee Portal");
				break loop;
			}
		}
	}

	public static void initialInstructions() {
		System.out.println("\nEnter 1 for Login");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");
	}

	public static void managerInstruction() {
		System.out.println("\nEnter 1 add Employees \nEnter 2 to Remove Employee \nEnter 3 view Sales history");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");

	}
}