package com.ersproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ersproject.model.User;
import com.ersproject.model.UserRoles;
import com.ersproject.utility.ConnectionUtilityAWS;

public class UserDAOImpl implements UserDAO {

	private static Logger log = LogManager.getLogger(UserDAOImpl.class);

	@Override
	public boolean validateLogin(String userName, String password) {
		log.info("validate login invoked!");
		boolean validdateLogin = false;
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM ers_db.ers_users WHERE ers_user_name = ? AND ers_password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("ers_user_name").equalsIgnoreCase(userName)
						&& rs.getString("ers_password").equals(password)) {
					validdateLogin = true;
				}
			}
		} catch (SQLException e) {
			log.error("validate login failed!");
			e.printStackTrace();
			return false;
		}
		log.info("validate login completed");
		return validdateLogin;
	}

	@Override
	public User viewPersonalInfo(String userName, String password) {
		log.info("view personal information invoked");
		User user = new User();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "SELECT * FROM ers_db.ers_users u "
					+ "join ers_db.ers_user_roles r on r.ers_user_role_id = u.user_role_id "
					+ "WHERE u.ers_user_name = ? AND u.ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setUser_id(rs.getInt("ers_user_id"));
				user.setUser_name(rs.getString("ers_user_name"));
				user.setUser_password(rs.getString("ers_password"));
				user.setUser_first_name(rs.getString("user_first_name"));
				user.setUser_last_name(rs.getString("user_last_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_role_id(new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
			}
		} catch (SQLException e) {
			log.debug("view personal information failed");
			e.printStackTrace();
		}
		log.info("view personal information completed");
		return user;
	}

	@Override
	public boolean updatePersonalInfo(User user) {
		log.info("update personal information invoked");
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "update ers_db.ers_users set user_first_name = ?, user_last_name = ?, user_email = ? "
					+ "where ers_user_name = ? and ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getUser_first_name());
			ps.setString(2, user.getUser_last_name());
			ps.setString(3, user.getUser_email());
			ps.setString(4, user.getUser_name());
			ps.setString(5, user.getUser_password());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error("update personal information failed");
			e.printStackTrace();
			return false;
		}
		log.info("update personal information completed");
		return true;
	}

	@Override
	public List<User> listAllemployee() {
		log.info("view personal information invoked");
		List<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "SELECT * FROM ers_db.ers_users";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("ers_user_id"));
				user.setUser_name(rs.getString("ers_user_name"));
				user.setUser_password(rs.getString("ers_password"));
				user.setUser_first_name(rs.getString("user_first_name"));
				user.setUser_last_name(rs.getString("user_last_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_role_id(new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role")));
				users.add(user);
			}
		} catch (SQLException e) {
			log.debug("list all users failed");
			e.printStackTrace();
			return null;
		}
		log.info("list all users completed");
		return users;
	}
}
