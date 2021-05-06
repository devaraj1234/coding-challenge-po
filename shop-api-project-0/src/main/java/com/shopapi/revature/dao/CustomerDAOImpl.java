package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.utility.ConnectionUtility;

public class CustomerDAOImpl implements CustomerDAO{

	private static Logger log = LogManager.getLogger(CustomerDAOImpl.class);
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean add(Customer t) {
		log.info("add customer information invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.customers VALUES(default,?,?,?,?,?,?,?)");
			ps.setString(1, t.getCustomer_fname());
			ps.setString(2, t.getCustomer_lname());
			ps.setString(3, t.getCustomer_email());
			ps.setString(4, t.getCustomer_address());
			ps.setInt(5, t.getCustomer_phone());
			ps.setInt(6, t.getCustomer_ssn());
			ps.setInt(7, t.getCustomer_login().getLogin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("add customer information failed");
			e.printStackTrace();
			return false;
		}
		log.info("add customer information completed");
		return true;
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Customer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Customer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer getByLoginId(int loginId) {
		log.info("get customer by login id invoked");
		Customer customer = new Customer();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("SELECT * FROM shopapi.customers WHERE customer_login_id = ?");
			ps.setInt(1, loginId);
			rs = ps.executeQuery();
			while (rs.next()) {
				customer.setCustomer_id(rs.getInt("customer_id"));
				customer.setCustomer_fname(rs.getString("customer_fname"));
				customer.setCustomer_lname(rs.getString("customer_lname"));
				customer.setCustomer_email(rs.getString("customer_email"));
				customer.setCustomer_address(rs.getString("customer_address"));
				customer.setCustomer_phone(rs.getInt("customer_phone"));
				customer.setCustomer_ssn(rs.getInt("customer_ssn"));
				customer.setCustomer_login(new LoginDetails(rs.getInt("customer_login_id"), null, null, null));
			}
		} catch (SQLException e) {
			log.debug("get customer by login id failed");
			e.printStackTrace();
		}
		log.info("get customer by login id completed");
		return customer;
	}

}
