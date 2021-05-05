package com.shopapi.revature.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.Offeres;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.User;
import com.shopapi.revature.model.WeeklyCollection;
import com.shopapi.revature.service.CommonService;
import com.shopapi.revature.service.EmployeeService;

public class EmployeeApplication {

	private static Logger log = LogManager.getLogger(EmployeeApplication.class);

	public static void employeeActivities() {
		System.out.println("Welcome to Employee Portal of XYZ Auction Inc..!!!!!!!!!!!!!!!");
		CommonService commonService = new CommonService();
		EmployeeService employeeService = new EmployeeService();
		final String user = "Employee";
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
						System.out.println("User: " + login.getLogin_user() + "\t\t\t\tUser_role: "
								+ login.getUser_role().getUser_role());
						System.out.println("---------------------------------------------------------------------");
						System.out.println("\nLogin Success!!!!!!!! Please select the options");
						employeeloop: while (true) {
							employeeInstruction();
							String employeeMenu = scan.next();
							employeeSelection: switch (employeeMenu) {
							case "1": {
								System.out.println("Enter product name: ");
								scan.nextLine();
								String product_name = scan.nextLine();
								System.out.println("Enter product description: ");
								scan.nextLine();
								String product_description = scan.nextLine();
								System.out.println("Enter product quantity: ");
								int product_quantity = scan.nextInt();
								System.out.println("Minium expected bidding price per unit: ");
								double product_min_price = scan.nextDouble();
								boolean productAdded = employeeService.addProductToList(new Product(null, product_name,
										product_quantity, product_min_price, product_description));
								if (productAdded == true) {
									System.out.println("Product successfully added to the product table");
								}
								break employeeSelection;
							}
							case "2": {
								List<Product> products = employeeService.getAllProducts();
								System.out.println("\nList of availiabe products fro Auction...");
								System.out.println("--------------------------------------------------------");
								for (Product product : products) {
									System.out.println(product);
								}
								System.out.println("--------------------------------------------------------");
								break employeeSelection;
							}
							case "3": {
								System.out.println("Enter product id to remove product from list \nProduct id: ");
								int product_id = scan.nextInt();
								boolean isProductDeleted = employeeService.deleteProduct(new Product(product_id));
								if (isProductDeleted == true) {
									System.out.println("Product successfully deleted from database");
								}
								break employeeSelection;
							}
							case "4": {
								List<Offeres> offers = employeeService.getAllOfferMade();
								System.out.println("List for all the offers Received: ");
								System.out.println("----------------------------------------------------------------");
								for (Offeres offer : offers) {
									System.out.println("Offer No: " + offer.getOffer_no() + "\tProduct Id: "
											+ offer.getProduct().getProduct_id() + "\tProduct Name: "
											+ offer.getProduct().getProduct_name() + "\tQuantity Availiabe: "
											+ offer.getProduct().getProduct_quantity() + "\tOffered Quantity: "
											+ offer.getOffer_quantity() + "\tExpected Price Per Unit: "
											+ offer.getProduct().getexpected_price_per_unit()
											+ "\tOffered Price per Unit: " + offer.getOffered_price_per_unit()
											+ "\tPayment Made: " + offer.getPayment_made() + "\tOffer Status: "
											+ offer.getOffer_status());
								}
								System.out.println("----------------------------------------------------------------");
								offerloop: while (true) {
									System.out.println(
											"\nEnter 1 to view offers by product id \nEnter q for Exit \nEnter Here: ");
									String offerMenu = scan.next();
									switch (offerMenu) {
									case "1": {
										System.out.println("Enter product id to view by product: ");
										int product_id = scan.nextInt();
										System.out.println("List of offers for priduct id " + product_id + ":");
										System.out.println(
												"----------------------------------------------------------------");
										for (Offeres offer : offers) {
											if (offer.getProduct().getProduct_id() == product_id) {
												System.out.println("Offer No: " + offer.getOffer_no() + "\tProduct Id: "
														+ offer.getProduct().getProduct_id() + "\tProduct Name: "
														+ offer.getProduct().getProduct_name()
														+ "\tQuantity Availiabe: "
														+ offer.getProduct().getProduct_quantity()
														+ "\tOffered Quantity: " + offer.getOffer_quantity()
														+ "\tExpected Price Per Unit: "
														+ offer.getProduct().getexpected_price_per_unit()
														+ "\tOffered Price per Unit: "
														+ offer.getOffered_price_per_unit() + "\tPayment Made: "
														+ offer.getPayment_made() + "\tOffer Status: "
														+ offer.getOffer_status());
											}
										}
										System.out.println(
												"----------------------------------------------------------------");

										System.out.println(
												"\nEnter 1 to Accept Offer \nEnter 2 to Reject Offer \nEnter q to Exit \nEnter Here:");
										String employeeInput = scan.next();
										if (employeeInput.equals("1")) {
											System.out.println("Enter offer no to accept offer: ");
											int offer_no = scan.nextInt();
											employeeService.acceptOffer(new Offeres(offer_no, new Product(product_id)));
										} else if (employeeInput.equals("2")) {
											System.out.println("Enter offer no to accept offer: ");
											int offer_no = scan.nextInt();
											employeeService.rejectOffer(offer_no);
										}
										break offerloop;
									}
									case "q":
										break offerloop;
									}
									break offerloop;
								}
								break employeeSelection;
							}
							case "5": {
								List<AccountCollection> accountCollection = employeeService.viewAllPaymentMade();
								System.out.println("List of all payments received: ");
								System.out.println(
										"---------------------------------------------------------------------");
								for (AccountCollection accountColl : accountCollection) {
									System.out.println("Collection Id: " + accountColl.getCollection_id()
											+ "\tProduct Order No: " + accountColl.getSales_order_no().getOrder_no()
											+ "\t Customer Name: "
											+ accountColl.getSales_order_no().getCustomer().getCustomer_fname() + " "
											+ accountColl.getSales_order_no().getCustomer().getCustomer_lname()
											+ "\t\tTotal Price: " + accountColl.getTotal_price()
											+ "\tPayment Date: " + accountColl.getPayment_date() 
											+ "\tPayment Received : " + accountColl.getPayment_made()
											+ "\tRemaining Balance: " + accountColl.getRemaining_balance());
								}
								System.out.println(
										"---------------------------------------------------------------------");
								System.out.println(
										"\nEnter 1 to view Weekly Collection \nEnter q for Exit \nEnter Here: ");
								String weekCollection = scan.next();
								if (weekCollection.equals("1")) {
									List<WeeklyCollection> weeklyCollections = employeeService.getWeeklyCollection();
									System.out.println("\nWeekly Collection Detail: ");
									for (WeeklyCollection weeklyCollection : weeklyCollections) {
										System.out.println(weeklyCollection);

									}
								}
								break employeeSelection;
							}

							default:
								break employeeSelection;
							case "q":
								break employeeloop;
							}
						}
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

	public static void employeeInstruction() {
		System.out.println(
				"\nEnter 1 to add products \nEnter 2 to view all products \nEnter 3 to remove products \nEnter 4 to view offers \nEnter 5 to view all collection");
		System.out.println("Enter 'q' for Exit");
		System.out.println("Enter Here: ");

	}
}
