package com.ersproject.dao;

import java.io.InputStream;
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
import com.ersproject.model.ReimbursementType;
import com.ersproject.model.User;
import com.ersproject.model.UserRoles;
import com.ersproject.utility.ConnectionUtilityAWS;

public class ReimDAOImpl implements ReimDAO {

	private static Logger log = LogManager.getLogger(ReimDAOImpl.class);

	@Override
	public boolean submitReimbursementRequest(Reimbursement newRequest) {
		log.info("submit reimbursement request method invoked");
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO ers_db.ers_reimbursement VALUES(default,?,?,?,?,?,?,null,1,?)");

			ps.setDouble(1, newRequest.getReimb_amount());
			ps.setDate(2, newRequest.getReimb_submitted());
			ps.setDate(3, newRequest.getReim_resolved());
			ps.setString(4, newRequest.getReimb_description());
			ps.setBinaryStream(5, (InputStream) newRequest.getReimb_receipt());
			ps.setInt(6, newRequest.getReimb_author().getUser_id());
			ps.setInt(7, newRequest.getReimb_type_id().getReim_type_id());
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
	public List<Reimbursement> viewReimbursementList(int user_id) {
		log.info("view reimbursement recored invoked");
		List<Reimbursement> reimbursmentList = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "select * from ers_db.ers_reimbursement r "
					+ "join ers_db.ers_reimbursement_status rs on rs.reimb_status_id = r.reimb_status_id "
					+ "join ers_db.ers_reimbursement_type et on et.reimb_type_id = r.reimb_type_id "
					+ "WHERE r.reimb_author = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				reim.setReimb_id(rs.getInt("reimb_id"));
				reim.setReimb_amount(rs.getDouble("reimb_amount"));
				reim.setReimb_submitted(rs.getDate("reimb_submitted"));
				reim.setReim_resolved(rs.getDate("reimb_resolved"));
				reim.setReimb_description(rs.getString("reimb_description"));
				reim.setReimb_receipt(rs.getBlob("reimb_receipt"));
				reim.setReimb_type_id(new ReimbursementType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				reim.setReimb_status_id(
						new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
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
	public int getReimbursementTypeId(String reim_type) {
		log.info("get reimbursement type id invoked");
		int reim_type_id = 0;
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT reimb_type_id FROM ers_db.ers_reimbursement_type WHERE reimb_type = ?");
			ps.setString(1, reim_type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reim_type_id = rs.getInt("reimb_type_id");
			}
		} catch (SQLException e) {
			log.error("get reimbursement type id failed");
			e.printStackTrace();
			return 0;
		}
		return reim_type_id;
	}

	@Override
	public boolean approveReimbursement(int request_id, String action, Date resolvedDate, int resolverId) {
		log.info("approve reimbursement method invoked");
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "update 	ers_db.ers_reimbursement er set reimb_status_id = ("
					+ "select reimb_status_id from ers_db.ers_reimbursement_status where reimb_status = 'approved'),"
					+ "reimb_resolved = ?, reimb_solver = ? "
					+ "where er.reimb_id =?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, resolvedDate);
			ps.setInt(2, resolverId);
			ps.setInt(3, request_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error("approve reimbursement method failed");
			e.printStackTrace();
			return false;
		}
		log.info("approve reimbursement method completed");
		return true;
	}

	@Override
	public boolean rejectReimbursement(int request_id, String action, Date resolvedDate, int resolverId) {
		log.info("reject reimbursement method invoked");
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "update 	ers_db.ers_reimbursement er set reimb_status_id = ("
					+ "select reimb_status_id from ers_db.ers_reimbursement_status where reimb_status = 'rejected'),"
					+ "reimb_resolved = ?, reimb_solver = ? "
					+ "where er.reimb_id =?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, resolvedDate);
			ps.setInt(2, resolverId);
			ps.setInt(3, request_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error("reject reimbursement method failed");
			e.printStackTrace();
			return false;
		}
		log.info("reject reimbursement method completed");
		return true;
	}

	@Override
	public List<Reimbursement> viewIndividualReimRequest(String first_name) {
		log.info("view individual reimbursement recored invoked");
		List<Reimbursement> allReimList = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "select * from ers_db.ers_reimbursement r "
					+ "join ers_db.ers_reimbursement_status rs on rs.reimb_status_id = r.reimb_status_id "
					+ "join ers_db.ers_reimbursement_type et on et.reimb_type_id = r.reimb_type_id "
					+ "join ers_db.ers_users eu on eu.ers_user_id = r.reimb_author "
					+ "join ers_db.ers_user_roles ur on ur.ers_user_role_id = eu.user_role_id "
					+ "where eu.user_first_name = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, first_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				reim.setReimb_id(rs.getInt("reimb_id"));
				reim.setReimb_amount(rs.getDouble("reimb_amount"));
				reim.setReimb_submitted(rs.getDate("reimb_submitted"));
				reim.setReim_resolved(rs.getDate("reimb_resolved"));
				reim.setReimb_description(rs.getString("reimb_description"));
				reim.setReimb_receipt(rs.getBlob("reimb_receipt"));
				reim.setReimb_type_id(new ReimbursementType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				reim.setReimb_author(new User(rs.getInt("ers_user_id"), rs.getString("ers_user_name"),
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"),
						rs.getString("user_email"),
						new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role"))));
				reim.setReimb_status_id(
						new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				allReimList.add(reim);
			}
		} catch (SQLException e) {
			log.debug("view individual reimbursement recored failed");
			e.printStackTrace();
		}
		log.info("view individual reimbursement recored completed");
		return allReimList;
	}
	

	@Override
	public List<Reimbursement> getResolvedReimbursement() {
		log.info("view reimbursement recored invoked");
		List<Reimbursement> resolvedReimList = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_description, r.reimb_receipt, "
					+ "e.user_first_name, e.user_last_name, "
					+ "m.user_first_name as resolver_fname, m.user_last_name as resolver_lname, "
					+ "rt.reimb_type, rs.reimb_status from ers_db.ers_reimbursement r "
					+ "join ers_db.ers_users e on e.ers_user_id = r.reimb_author "
					+ "join ers_db.ers_users m on m.ers_user_id = r.reimb_solver "
					+ "join ers_db.ers_reimbursement_type rt on rt.reimb_type_id = r.reimb_type_id "
					+ "join ers_db.ers_reimbursement_status rs on rs.reimb_status_id = r.reimb_status_id;";
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
				reim.setReimb_type_id(new ReimbursementType(null, rs.getString("reimb_type")));
				reim.setReimb_author(new User(null, null, null, rs.getString("user_first_name"),
						rs.getString("user_last_name"), null, new UserRoles()));
				reim.setReimb_status_id(
						new ReimbursementStatus(null, rs.getString("reimb_status")));
				reim.setReimb_resolver(new User(null, null, null, rs.getString("resolver_fname"),
						rs.getString("resolver_lname"), null, new UserRoles()));
				resolvedReimList.add(reim);
			}
		} catch (SQLException e) {
			log.debug("view reimbursement recored failed");
			e.printStackTrace();
		}
		log.info("view reimbursement recored completed");
		return resolvedReimList;
	}

	@Override
	public List<Reimbursement> allReimbursementRequest() {
		log.info("view reimbursement recored invoked");
		List<Reimbursement> allReimList = new ArrayList<>();
		try (Connection conn = ConnectionUtilityAWS.getConnection()) {
			String query = "select * from ers_db.ers_reimbursement r "
					+ "join ers_db.ers_reimbursement_status rs on rs.reimb_status_id = r.reimb_status_id "
					+ "join ers_db.ers_reimbursement_type et on et.reimb_type_id = r.reimb_type_id "
					+ "join ers_db.ers_users eu on eu.ers_user_id = r.reimb_author "
					+ "join ers_db.ers_user_roles ur on ur.ers_user_role_id = eu.user_role_id;";
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
				reim.setReimb_type_id(new ReimbursementType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
				reim.setReimb_author(new User(rs.getInt("ers_user_id"), rs.getString("ers_user_name"),
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"),
						rs.getString("user_email"),
						new UserRoles(rs.getInt("ers_user_role_id"), rs.getString("user_role"))));
				reim.setReimb_status_id(
						new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")));
				allReimList.add(reim);
			}
		} catch (SQLException e) {
			log.debug("view reimbursement recored failed");
			e.printStackTrace();
		}
		log.info("view reimbursement recored completed");
		return allReimList;
	}
}
