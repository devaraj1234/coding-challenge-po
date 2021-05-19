package com.ersproject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ersproject.model.Reimbursement;
import com.ersproject.model.ReimbursementStatus;
import com.ersproject.model.User;
import com.ersproject.model.UserRoles;
import com.ersproject.utility.ConnectionUtilityAWS;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = LogManager.getLogger(EmployeeDAOImpl.class);


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
	public boolean submitReimbursementRequest(Reimbursement newRequest) {
		log.info("submit reimbursement request method invoked");
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO ers_db.ers_reimbursement VALUES(default,?,?,?,?,?,?,?,1,?)");

			ps.setDouble(1, newRequest.getReimb_amount());
			ps.setDate(2, (Date) newRequest.getReimb_submitted());
			ps.setDate(3, (Date) newRequest.getReim_resolved());
			ps.setString(4, newRequest.getReimb_description());
			ps.setBlob(5, newRequest.getReimb_receipt());
			ps.setInt(6, newRequest.getReimb_author().getUser_id());
			ps.setInt(7, newRequest.getReimb_resolver().getUser_id());
			ps.setInt(8, newRequest.getReimb_type_id().getReim_type_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error("add login details failed");
			e.printStackTrace();
			return false;
		}
		log.info("submit reimbursement request method completed");
		return true;
	}

	@Override
	public List<Reimbursement> viewPendingRequest() {
		log.info("view reimbursement recored invoked");
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "select * from ers_db.ers_reimbursement "
					+ "join ers_db.ers_reimbursement_status on ers_db.ers_reimbursement_status.reimb_status_id = ers_db.ers_reimbursement.reimb_type_id;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				reim.setReimb_id(rs.getInt("reimb_id"));
				reim.setReimb_amount(rs.getDouble("reimb_amount"));
				reim.setReimb_submitted(rs.getDate("reimb_submitted"));
				reim.setReim_resolved(rs.getDate("reimb_resolved"));
				reim.setReimb_description(rs.getString("reimb_description"));
				reim.setReimb_receipt(rs.getBlob("reimb_receipt"));
				reim.setReimb_status_id(new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				reimbursmentList.add(reim);
			}
		} catch (SQLException e) {
			log.debug("view reimbursement recored failed");
			e.printStackTrace();
		}
		log.info("view reimbursement recored completed");
		return reimbursmentList;
	}

	@Override
	public List<Reimbursement> viewResolvedRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewAllRequest() {
		// TODO Auto-generated method stub
		return null;
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
	public boolean updatePersonalInfo(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
