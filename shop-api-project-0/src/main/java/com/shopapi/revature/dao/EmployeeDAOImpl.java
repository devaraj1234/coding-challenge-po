package com.shopapi.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shopapi.revature.model.Employee;
import com.shopapi.revature.utility.ConnectionUtility;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = LogManager.getLogger(EmployeeDAOImpl.class);

	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean addEmployee(Employee employee) {
		log.info("add employee details invoked");
		Connection conn = null;
		try {
			conn = ConnectionUtility.getConnection();
			conn.setAutoCommit(false);
			log.info("successfully connected to data base");
			String query = "begin transaction;" 
					+ "insert into shopapi.login_details values (default, ?, ?, ?); "
					+ "insert  into shopapi.employees values (default, ?, ?, ?, ?, "
					+ "(select login_id from shopapi.login_details where login_user = ? and login_password = ?)); "
					+ "commit;";
			ps = conn.prepareStatement(query);
			ps.setString(1, employee.getLogin().getLogin_user());
			ps.setString(2, employee.getLogin().getLogin_password());
			ps.setInt(3, employee.getLogin().getUser_role().getUser_id());
			ps.setString(4, employee.getEmployee_fname());
			ps.setString(5, employee.getEmployee_lname());
			ps.setString(6, employee.getEmployee_email());
			ps.setString(7, employee.getEmployee_position());
			ps.setString(8, employee.getLogin().getLogin_user());
			ps.setString(9, employee.getLogin().getLogin_password());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("add employee details failed");
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		log.info("add employee details success");
		return true;
	}

	@Override
	public boolean removeEmployee(int employee_id) {
		log.info("remove employee record method invoked");
		try (Connection conn = ConnectionUtility.getConnection()){
			log.info("successfully connected to data base");
			String query = "delete from shopapi.employees where employee_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, employee_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			log.debug("remove employee details failed");
			e.printStackTrace();
			return false;
		}
		log.info("remove employee record method completed");
		return true;
	}

}
