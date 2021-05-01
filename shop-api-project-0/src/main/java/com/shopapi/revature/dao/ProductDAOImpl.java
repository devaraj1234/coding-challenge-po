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

import com.shopapi.revature.model.Product;
import com.shopapi.revature.model.ProductOwned;
import com.shopapi.revature.utility.ConnectionUtility;

public class ProductDAOImpl implements ProductDAO {

	private static Logger log = LogManager.getLogger(ProductDAOImpl.class);
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean add(Product t) {
		log.info("add product to the list invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.product VALUES (default,?,?,?,?)");
			ps.setString(1, t.getProduct_name());
			ps.setString(2, t.getProduct_description());
			ps.setInt(3, t.getProduct_quantity());
			ps.setDouble(4, t.getexpected_price_per_unit());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("add product to the list failed");
			e.printStackTrace();
			return false;
		}
		log.info("add product to the list completed");
		return true;
	}

	@Override
	public List<Product> getAll() {
		log.info("list all products invoked");
		List<Product> products = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM shopapi.product");
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_quantity(rs.getInt("availiable_quantity"));
				product.setProduct_description(rs.getString("product_description"));
				product.setexpected_price_per_unit(rs.getDouble("expected_price_per_unit"));
				products.add(product);
			}
		} catch (SQLException e) {
			log.debug("list user failed");
			e.printStackTrace();
			return null;
		}
		log.info("list user completed");
		return products;
	}

	@Override
	public boolean update(Product t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Product t) {
		log.info("delete product from the list invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("DELETE FROM shopapi.product WHERE shopapi.product.product_id = ?");
			ps.setInt(1, t.getProduct_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("delete product from the list failed");
			e.printStackTrace();
			return false;
		}
		log.info("delete product from the list completed");
		return true;
	}

	@Override
	public List<ProductOwned> viewAllProductOwned(ProductOwned owner) {
		log.info("list all products invoked");
		List<ProductOwned> products = new ArrayList<>();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			String query = "select * from shopapi.product_owner "
					+ "join shopapi.product on shopapi.product.product_id = shopapi.product_owner.product_owned "
					+ "where shopapi.product_owner.product_owner = ?;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, owner.getProduct_owner().getCustomer_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductOwned product = new ProductOwned();
				product.setOrder_no(rs.getInt("order_no"));
				product.setProduct_owned(new Product(rs.getString("product_name")));
				product.setOwned_quantity(rs.getInt("owned_quantity"));
				product.setOwned_date(rs.getDate("product_owned_date"));
				product.setOwned_status(rs.getString("product_owned_status"));
				products.add(product);
			}
		} catch (SQLException e) {
			log.debug("list user failed");
			e.printStackTrace();
			return null;
		}
		log.info("list user completed");
		return products;
	}
}
