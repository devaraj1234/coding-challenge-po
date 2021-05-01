package com.shopapi.revature.controller;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.Exceptions.InvalidLoginException;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.User;
import com.shopapi.revature.service.CommonService;

public class ManagerApplication {

	private static Logger log = LogManager.getLogger(EmployeeApplication.class);

	public static void managerialActivities() {
		System.out.println("Welcome to Manager Portal of XYZ Auction Inc..!!!!!!!!!!!!!!!");
		CommonService commonService = new CommonService();
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
							// employeeInstruction();
							String employeeMenu = scan.next();
							managerSelection: switch (employeeMenu) {
							case "1": {

								break managerSelection;
							}
							case "2": {

								break managerSelection;
							}
							case "3": {

								break managerSelection;
							}
							case "4": {

								break managerSelection;
							}
							case "5": {

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
}