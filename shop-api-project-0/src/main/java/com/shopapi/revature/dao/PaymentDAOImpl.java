package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.AccountCollection;
import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.ProductOwned;
import com.shopapi.revature.utility.ConnectionUtility;

public class PaymentDAOImpl implements PaymentDAO {

	public static Logger log = LogManager.getLogger(UserDAOImpl.class);
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public List<AccountCollection> viewAllPayment() {
		log.info("view all payments invoked");
		List<AccountCollection> accountCollection = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "select * from shopapi.account_collection "
					+ "join shopapi.product_owner on shopapi.product_owner.order_no = shopapi.account_collection.product_order_no "
					+ "join shopapi.customers on shopapi.customers.customer_id = shopapi.product_owner.product_owner;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				AccountCollection accountColl = new AccountCollection();
				accountColl.setCollection_id(rs.getInt("collection_id"));
				accountColl.setproduct_owner(new ProductOwned(rs.getInt("order_no"), new Customer(rs.getString("customer_fname"),
						rs.getString("customer_lname"))));
				accountColl.setTotal_price(rs.getDouble("total_price"));
				accountColl.setPayment_made(rs.getDouble("payment_made"));
				accountColl.setRemaining_balance(rs.getDouble("remaining_payment"));
				accountColl.setPayment_date(rs.getDate("payment_date"));
				accountCollection.add(accountColl);
			}

		} catch (SQLException e) {
			log.debug("view all payments invoked");
			e.printStackTrace();
			return null;
		}
		log.info("view all payments completed");
		return accountCollection;
	}

	@Override
	public List<AccountCollection> viewAllPaymentForCustomer(AccountCollection Customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
