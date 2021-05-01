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
import com.shopapi.revature.model.OfferedMade;
import com.shopapi.revature.model.Product;
import com.shopapi.revature.utility.ConnectionUtility;

public class OfferedMadeDAOImpl implements OfferMadeDAO {

	private static Logger log = LogManager.getLogger(LoginDetailsDAOImpl.class);

	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean add(OfferedMade t) {
		log.info("add to offer table invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.offer_made VALUES(default,?,?,?,?,?,?,?)");
			ps.setInt(1, t.getProduct().getProduct_id());
			ps.setInt(2, t.getProduct_owner().getCustomer_id());
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
		log.info("add to offer table completed");
		return true;
	}

	@Override
	public List<OfferedMade> getAll() {
		log.info("list all offer made invoked");
		List<OfferedMade> offers = new ArrayList<>();

		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "select * from shopapi.offer_made "
					+ "join shopapi.product on shopapi.product.product_id = shopapi.offer_made.offer_product "
					+ "where shopapi.offer_made.product_offer_status = 'pending';";
			log.info("successfully connected to data base");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				OfferedMade offer = new OfferedMade();
				offer.setOffer_no(rs.getInt("offer_no"));
				offer.setProduct(new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getInt("availiable_quantity"), rs.getDouble("expected_price_per_unit"),
						rs.getString("product_description")));
				offer.setProduct_owner(new Customer(rs.getInt("product_owner")));
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
	public boolean update(OfferedMade t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(OfferedMade t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptOffer(OfferedMade offer) {
		boolean isAccepted = false;
		Connection conn = null;
		try {
			conn = ConnectionUtility.getConnection();
			conn.setAutoCommit(false);
			String query = "begin transaction; "
					+ "update shopapi.offer_made set product_offer_status = 'rejected' where offer_product = ?; "
					+ "update shopapi.offer_made set product_offer_status = 'owned' where offer_no = ?; "
					+ "insert into shopapi.product_owner (order_no, product_owned, product_owner, owned_quantity, product_owned_date, product_owned_status) "
					+ "select offer_no, offer_product, product_owner, offered_quantity, product_offer_date, product_offer_status "
					+ "from shopapi.offer_made where offer_no = ?; "
					+ "insert into shopapi.account_collection (product_order_no, offered_price_per_unit, total_price, payment_made, remaining_payment, payment_date) "
					+ "select offer_no, offered_price_per_unit, (offered_quantity*offered_price_per_unit), payment_made, ((offered_quantity*offered_price_per_unit)-payment_made), product_offer_date "
					+ "from shopapi.offer_made where offer_no = ?; " 
					+ "commit;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, offer.getProduct().getProduct_id());
			ps.setInt(2, offer.getOffer_no());
			ps.setInt(3, offer.getOffer_no());
			ps.setInt(4, offer.getOffer_no());
			ps.executeUpdate();
			isAccepted = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isAccepted;
	}
}
