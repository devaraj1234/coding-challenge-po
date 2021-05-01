package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.LoginDetails;
import com.shopapi.revature.model.User;
import com.shopapi.revature.utility.ConnectionUtility;

public class LoginDetailsDAOImpl implements LoginDetailsDAO {

	private static Logger log = LogManager.getLogger(LoginDetailsDAOImpl.class);
	
	LoginDetails loginInfo;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean add(LoginDetails t) {
		log.info("add login details invoked");
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("INSERT INTO shopapi.login_details VALUES(default,?,?,?)");
			ps.setString(1, t.getLogin_user());
			ps.setString(2, t.getLogin_password());
			ps.setInt(3, t.getUser_role().getUser_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("add login details failed");
			e.printStackTrace();
			return false;
		}
		log.info("add login details success");
		return true;
	}

	@Override
	public List<LoginDetails> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(LoginDetails t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(LoginDetails t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateLogin(LoginDetails login) {
		log.info("validate login invoked!");
		boolean validdateLogin = false;
		loginInfo = new LoginDetails();
		try (Connection conn = ConnectionUtility.getConnection()) {
			log.info("successfully connected to data base");
			ps = conn.prepareStatement("SELECT * FROM shopapi.login_details "
					+ "JOIN shopapi.users on shopapi.users.user_id = shopapi.login_details.user_role_id "
					+ "WHERE login_user = ? AND login_password = ?");
			ps.setString(1, login.getLogin_user());
			ps.setString(2, login.getLogin_password());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("login_user").equalsIgnoreCase(login.getLogin_user())
						&& rs.getString("login_password").equals(login.getLogin_password())
						&& rs.getString("user_role").equalsIgnoreCase(login.getUser_role().getUser_role())) {
					
					loginInfo.setLogin_id(rs.getInt("login_id"));
					loginInfo.setLogin_user(rs.getString("login_user"));
					loginInfo.setUser_role(new User(rs.getInt("user_id"), rs.getString("user_role")));
					validdateLogin = true;
				}
			}
		} catch (SQLException e) {
			log.info("validate login failed!");
			e.printStackTrace();
			return false;
		}
		log.info("validate login completed");
		return validdateLogin;
	}

	@Override
	public LoginDetails getLoginDetail() {
		return loginInfo;
	}
}
