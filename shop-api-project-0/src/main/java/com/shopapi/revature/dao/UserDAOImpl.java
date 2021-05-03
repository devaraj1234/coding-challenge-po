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

import com.shopapi.revature.model.User;
import com.shopapi.revature.utility.ConnectionUtility;

public class UserDAOImpl implements UserDAO {

	public static Logger log = LogManager.getLogger(UserDAOImpl.class);
	PreparedStatement ps = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean add(User t) {
		log.info("create user invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.users VALUES(default, ?);");
			ps.setString(1, t.getUser_role());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("create user failed");
			e.printStackTrace();
			return false;
		}
		log.info("create user success");
		return true;
	}

	@Override
	public List<User> getAll() {
		log.info("list user invoked");
		List<User> users = new ArrayList<>();

		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM shopapi.users");

			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_role(rs.getString("user_role"));
				users.add(user);
			}

		} catch (SQLException e) {
			log.debug("list user failed");
			e.printStackTrace();
			return null;
		}
		log.info("list user completed");
		return users;
	}

	@Override
	public boolean update(User t) {
		log.info("update user invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("UPDATE shopapi.users SET user_role =? WHERE user_id =?");
			ps.setString(1, t.getUser_role());
			ps.setInt(2, t.getUser_id());
			ps.executeUpdate();

		} catch (SQLException e) {
			log.debug("update user failed");
			e.printStackTrace();
			return false;
		}
		log.info("update user completed");
		return true;
	}

	@Override
	public boolean delete(User t) {
		log.info("delete user invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("DELETE FROM shopapi.users WHERE user_id =?");
			ps.setInt(1, t.getUser_id());
			ps.executeUpdate();

		} catch (SQLException e) {
			log.debug("delete user failed");
			e.printStackTrace();
			return false;
		}
		log.info("delete user completed");
		return true;
	}
}
