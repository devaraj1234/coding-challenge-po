package com.shopapi.revature.controller;

import java.util.Scanner;

public class MainSimulator {
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("\nWelcome to XYZ Auction Inc.!!!!!!!!!!!!!!!");
		loop: while (true) {
			initialInstructions();
			if (scan.hasNextLine())
				;
			String menu = scan.next();
			selection: switch (menu) {
			case "1": {
				ManagerApplication.managerialActivities();;
				break selection;
			}
			case "2": {
				EmployeeApplication.employeeActivities();
				break selection;
			}
			case "3": {
				CustomerApplication.customerActivities();
				break selection;
			}
			default:
				System.out.println("Wrong Input!!!!!!! Try Again");
				break selection;
			case "q":
				System.out.println("Thank you for Using XYZ Auction Inc. Portal");
				break loop;
			}
		}
		scan.close();
	}

	public static void initialInstructions() {
		System.out.println("\nEnter 1 for Manager Login \nEnter 2 for Employee Login \nEnter 3 for Customer/User");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");
	}
}
