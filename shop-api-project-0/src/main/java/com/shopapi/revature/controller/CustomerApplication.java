package com.shopapi.revature.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.Exceptions.InvalidLoginException;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.User;
import com.shopapi.revature.service.CommonService;
import com.shopapi.revature.service.CustomerService;

public class CustomerApplication {

	private static Logger log = LogManager.getLogger(CustomerApplication.class);

	public static void customerActivities() {
		log.info("customer application portal started");
		System.out.println("Welcome to Customer Portal of XYZ Auction Inc..!!!!!!!!!!!!!!!");
		final String user = "customer";
		CommonService commonService = new CommonService();
		CustomerService customerService = new CustomerService();
		Scanner scan = MainSimulator.scan;
		loop: while (true) {
			initialInstructions();
			String menu = scan.next();
			selection: switch (menu) {
			case "1": {
				try {
					log.info("create user login function started");
					System.out.println("Enter User Name: ");
					String userName = scan.next();
					System.out.println("Enter User Password: ");
					String userPassword = scan.next();
					int userId = commonService.getUserId(user);
					commonService.createLogin(new LoginDetails(null, userName, userPassword, new User(userId, null)));
					log.info("create user account function completed");
				} catch (Exception e) {
					log.debug("crete user account function failed");
					e.printStackTrace();
				}
				break selection;
			}
			case "2": {
				try {
					log.info("user login function stared");
					System.out.println("Enter User Name: ");
					String userName = scan.next();
					System.out.println("Enter User Password: ");
					String userPassword = scan.next();

					boolean loginStaus = commonService
							.validateUserLogin(new LoginDetails(null, userName, userPassword, new User(null, user)));

					if (loginStaus == true) {
						log.info("user login success");
						LoginDetails login = commonService.getLoginDetails();
						int login_id = login.getLogin_id();
						int customer_id;
						customer_id = customerService.getCustomerId(login_id);
						if (customer_id <= 0) {
							System.out.println("Please provide customer details:");
							System.out.println("Enter First Name: ");
							String fname = scan.next();
							System.out.println("Enter Last Name: ");
							String lname = scan.next();
							System.out.println("Enter Email: ");
							String email = scan.next();
							System.out.println("Enter Address: ");
							String address = scan.next();
							System.out.println("Enter Phone: ");
							int phone = scan.nextInt();
							System.out.println("Enter SSN: ");
							int ssn = scan.nextInt();
							boolean addCustomer = customerService.addCustomer(new Customer(null, fname, lname, email,
									address, phone, ssn, new LoginDetails(login_id, null, null, null)));
							if (addCustomer == true) {
								customer_id = customerService.getCustomerId(login_id);
								System.out.println("Successfully added new customer to data base");
							}
						}
						if (customer_id > 0) {
							System.out.println("--------------------------------------------------------------------");
							System.out.println("User: " + login.getLogin_user() + "\t\t\t\t\tUser_role: "
									+ login.getUser_role().getUser_role());
							System.out.println("---------------------------------------------------------------------");
							customerloop: while (true) {
								customerInstruction();
								String customermenu = scan.next();
								customerSelection: switch (customermenu) {
								case "1": {
									List<Product> products = customerService.getAllProducts();
									System.out.println("\nList of availiabe products fro Auction...");
									System.out.println("--------------------------------------------------------");
									for (Product product : products) {
										System.out.println(product);
									}
									System.out.println("--------------------------------------------------------");
									offerloop: while (true) {
										System.out.println("\nEnter 1 to make offer \nEnter q to Exit \nEnter Here: ");
										String offerMenu = scan.next();
										offerMenu: switch (offerMenu) {
										case "1": {
											System.out.println("Enter Product id to make offer: ");
											int product_id = scan.nextInt();
											Product offeredProduct = null;
											for (Product product : products) {
												if (product.getProduct_id() == product_id) {
													offeredProduct = product;
												}
											}
											if (offeredProduct != null) {
												System.out.println("Product Detail: \n" + offeredProduct);
												System.out.println("Enter quantity you want to offer: ");
												int quantity = scan.nextInt();
												System.out.println("Enter price (per unit) you want to offer: ");
												double offered_price = scan.nextDouble();
												System.out.println("Enter price you want to pay today: ");
												double down_payment = scan.nextDouble();

												long millis = System.currentTimeMillis();
												java.sql.Date date = new java.sql.Date(millis);

												final String ownedStatus = "pending";
												while (true) {
													System.out.println("Enter 1 to confirm \n Enter q for Exit");
													String confirmMenu = scan.next();
													switch (confirmMenu) {
													case "1": {
														customerService.offerMadeForProduct(
																new OfferedMade(null, new Product(product_id),
																		new Customer(customer_id), quantity, date,
																		offered_price, down_payment, ownedStatus));
														break offerloop;
													}
													case "q": {
														break offerloop;
													}
													}
												}
											} else {
												System.out.println("Sorry!!!!!!This product is not availiable.");
											}
											break offerloop;
										}
										case "q":
											break offerloop;
										}
									}
									break customerSelection;
								}
								case "2": {
									System.out.println("List of products you owned are: ");

									System.out.println(
											"-------------------------------------------------------------------");

									break customerSelection;
								}
								case "3": {

									break customerSelection;
								}
								case "q": {
									break customerloop;
								}
								}

							}

						}
					} else {
						throw new InvalidLoginException("user name or password is incorrect. Please tyr again.");
					}

				} catch (Exception e) {
					log.info("user login function failed");
					e.printStackTrace();
				}
				break selection;
			}
			default:
				System.out.println("Wrong Input!!!!!!! Try Again");
				break selection;
			case "q":
				System.out.println("Thank you for Using Customer Portal");
				break loop;
			}
		}
	}

	public static void initialInstructions() {
		System.out.println("\nEnter 1 to Create new Login OR Enter 2 for Login..");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");
	}

	public static void customerInstruction() {
		System.out.println(
				"\nEnter 1 to view availiabe items \nEnter 2 to view items owned \nEnter 3 to view remaining payment");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");
	}

}
