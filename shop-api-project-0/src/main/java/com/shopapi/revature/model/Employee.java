package com.shopapi.revature.model;

public class Employee {

	private Integer employee_id;
	private String employee_fname;
	private String employee_lname;
	private String employee_email;
	private String employee_position;
	private LoginDetails login;

	public Employee() {
	}

	public Employee(Integer employee_id, String employee_fname, String employee_lname, String employee_email,
			String employee_position, LoginDetails login) {
		super();
		this.employee_id = employee_id;
		this.employee_fname = employee_fname;
		this.employee_lname = employee_lname;
		this.employee_email = employee_email;
		this.employee_position = employee_position;
		this.login = login;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_fname() {
		return employee_fname;
	}

	public void setEmployee_fname(String employee_fname) {
		this.employee_fname = employee_fname;
	}

	public String getEmployee_lname() {
		return employee_lname;
	}

	public void setEmployee_lname(String employee_lname) {
		this.employee_lname = employee_lname;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public String getEmployee_position() {
		return employee_position;
	}

	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}

	public LoginDetails getLogin() {
		return login;
	}

	public void setLogin(LoginDetails login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_email == null) ? 0 : employee_email.hashCode());
		result = prime * result + ((employee_fname == null) ? 0 : employee_fname.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((employee_lname == null) ? 0 : employee_lname.hashCode());
		result = prime * result + ((employee_position == null) ? 0 : employee_position.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employee_email == null) {
			if (other.employee_email != null)
				return false;
		} else if (!employee_email.equals(other.employee_email))
			return false;
		if (employee_fname == null) {
			if (other.employee_fname != null)
				return false;
		} else if (!employee_fname.equals(other.employee_fname))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (employee_lname == null) {
			if (other.employee_lname != null)
				return false;
		} else if (!employee_lname.equals(other.employee_lname))
			return false;
		if (employee_position == null) {
			if (other.employee_position != null)
				return false;
		} else if (!employee_position.equals(other.employee_position))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", employee_fname=" + employee_fname + ", employee_lname="
				+ employee_lname + ", employee_email=" + employee_email + ", employee_position=" + employee_position
				+ ", login=" + login + "]";
	}

}
