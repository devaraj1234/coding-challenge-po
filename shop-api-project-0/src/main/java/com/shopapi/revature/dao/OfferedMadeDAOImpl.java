package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.Customer;
import com.shopapi.revature.model.Offeres;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.utility.ConnectionUtility;

public class OfferedMadeDAOImpl implements OfferMadeDAO {

	private static Logger log = LogManager.getLogger(LoginDetailsDAOImpl.class);

	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean add(Offeres t) {
		log.info("add to offer table invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.offers VALUES(default,?,?,?,?,?,?,?)");
			ps.setInt(1, t.getProduct().getProduct_id());
			ps.setInt(2, t.getCustomer().getCustomer_id());
			ps.setInt(3, t.getOffer_quantity());
			ps.setDate(4, (Date) t.getOffer_date());
			ps.setDouble(5, t.getOffered_price_per_unit());
			ps.setDouble(6, t.getPayment_made());
			ps.setString(7, t.getOffer_status());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("add to offer table  failed");
			e.printStackTrace();
			return false;
		}
		log.info("offer successfull added to the offer table");
		return true;
	}

	@Override
	public List<Offeres> getAll() {
		log.info("list all offer made invoked");
		List<Offeres> offers = new ArrayList<>();

		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "select * from shopapi.offers "
					+ "join shopapi.product on shopapi.product.product_id = shopapi.offers.offer_product "
					+ "where shopapi.offers.product_offer_status = 'pending';";
			log.info("successfully connected to data base");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Offeres offer = new Offeres();
				offer.setOffer_no(rs.getInt("offer_no"));
				offer.setProduct(new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getInt("availiable_quantity"), rs.getDouble("expected_price_per_unit"),
						rs.getString("product_description")));
				offer.setCustomer(new Customer(rs.getInt("customer")));
				offer.setOffer_quantity(rs.getInt("offered_quantity"));
				offer.setOffer_date(rs.getDate("product_offer_date"));
				offer.setOffered_price_per_unit(rs.getDouble("offered_price_per_unit"));
				offer.setPayment_made(rs.getDouble("payment_made"));
				offer.setOffer_status(rs.getString("product_offer_status"));
				offers.add(offer);
			}

		} catch (SQLException e) {
			log.debug("list all offer made failed");
			e.printStackTrace();
			return null;
		}
		log.info("list all offer made completed");
		return offers;
	}

	@Override
	public boolean update(Offeres t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Offeres t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptOffer(Offeres offer) {
		log.info("accept offer by offer id invoked");
		boolean isAccepted = false;
		Connection conn = null;
		try {
			conn = ConnectionUtility.getConnection();
			log.info("successfull connected to database");
			conn.setAutoCommit(false);
			String query = "begin transaction; "
					+ "update shopapi.offers set product_offer_status = 'rejected' where offer_product = ?; "
					+ "update shopapi.offers set product_offer_status = 'owned' where offer_no = ?; "
					+ "insert into shopapi.sales (order_no, product, customer, sales_quantity, price_per_unit, sales_date, sales_status) "
					+ "select offer_no, offer_product, customer, offered_quantity, offered_price_per_unit, product_offer_date, product_offer_status "
					+ "from shopapi.offers where offer_no = ?; "
					+ "insert into shopapi.account_collection (product_order_no, offered_price_per_unit, total_price, payment_made, remaining_payment, payment_date) "
					+ "select offer_no, offered_price_per_unit, (offered_quantity*offered_price_per_unit), payment_made, ((offered_quantity*offered_price_per_unit)-payment_made), product_offer_date "
					+ "from shopapi.offers where offer_no = ?; "
					+ "update shopapi.product set shopapi.product.availiable_quantity = (availiable_quantity - (select offered_quantity from shopapi.offers where offer_no = ?)) where shopapi.product.product_id = ?;"
					+ "commit;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, offer.getProduct().getProduct_id());
			ps.setInt(2, offer.getOffer_no());
			ps.setInt(3, offer.getOffer_no());
			ps.setInt(4, offer.getOffer_no());
			ps.setInt(5, offer.getOffer_no());
			ps.setInt(6, offer.getProduct().getProduct_id());
			ps.executeUpdate();
			isAccepted = true;
		} catch (SQLException e) {
			log.debug("accept offer by offer id failed");
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		log.info("accept offer method comleted successfully");
		return isAccepted;
	}

	@Override
	public boolean rejectOffer(int offer_no) {
		log.info("reject offer invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to database");
			ps = conn.prepareStatement(
					"update shopapi.offers set product_offer_status = 'rejected' where offer_no = ?");
			ps.setInt(1, offer_no);
			ps.executeUpdate();
			log.info("offer rejected successfully");
			return true;
		} catch (SQLException e) {
			log.debug("reject offer failed");
			e.printStackTrace();
			return false;
		}
	}
}
