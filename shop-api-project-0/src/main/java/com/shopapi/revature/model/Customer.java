package com.shopapi.revature.model;

public class Customer {

	private Integer customer_id;
	private String customer_fname;
	private String customer_lname;
	private String customer_address;
	private String customer_email;
	private int customer_phone;
	private int customer_ssn;
	private LoginDetails customer_login;

	public Customer() {
	}
	
	public Customer(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Customer(String customer_fname, String customer_lname) {
		this.customer_fname = customer_fname;
		this.customer_lname = customer_lname;
	}

	public Customer(Integer customer_id, String customer_fname, String customer_lname, String customer_address,
			String customer_email, int customer_phone, int customer_ssn, LoginDetails customer_login) {
		super();
		this.customer_id = customer_id;
		this.customer_fname = customer_fname;
		this.customer_lname = customer_lname;
		this.customer_address = customer_address;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.customer_ssn = customer_ssn;
		this.customer_login = customer_login;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_fname() {
		return customer_fname;
	}

	public void setCustomer_fname(String customer_fname) {
		this.customer_fname = customer_fname;
	}

	public String getCustomer_lname() {
		return customer_lname;
	}

	public void setCustomer_lname(String customer_lname) {
		this.customer_lname = customer_lname;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public int getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(int customer_phone) {
		this.customer_phone = customer_phone;
	}

	public int getCustomer_ssn() {
		return customer_ssn;
	}

	public void setCustomer_ssn(int customer_ssn) {
		this.customer_ssn = customer_ssn;
	}

	public LoginDetails getCustomer_login() {
		return customer_login;
	}

	public void setCustomer_login(LoginDetails customer_login) {
		this.customer_login = customer_login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_address == null) ? 0 : customer_address.hashCode());
		result = prime * result + ((customer_email == null) ? 0 : customer_email.hashCode());
		result = prime * result + ((customer_fname == null) ? 0 : customer_fname.hashCode());
		result = prime * result + customer_id;
		result = prime * result + ((customer_lname == null) ? 0 : customer_lname.hashCode());
		result = prime * result + ((customer_login == null) ? 0 : customer_login.hashCode());
		result = prime * result + customer_phone;
		result = prime * result + customer_ssn;
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
		Customer other = (Customer) obj;
		if (customer_address == null) {
			if (other.customer_address != null)
				return false;
		} else if (!customer_address.equals(other.customer_address))
			return false;
		if (customer_email == null) {
			if (other.customer_email != null)
				return false;
		} else if (!customer_email.equals(other.customer_email))
			return false;
		if (customer_fname == null) {
			if (other.customer_fname != null)
				return false;
		} else if (!customer_fname.equals(other.customer_fname))
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (customer_lname == null) {
			if (other.customer_lname != null)
				return false;
		} else if (!customer_lname.equals(other.customer_lname))
			return false;
		if (customer_login == null) {
			if (other.customer_login != null)
				return false;
		} else if (!customer_login.equals(other.customer_login))
			return false;
		if (customer_phone != other.customer_phone)
			return false;
		if (customer_ssn != other.customer_ssn)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_fname=" + customer_fname + ", customer_lname="
				+ customer_lname + ", customer_address=" + customer_address + ", customer_email=" + customer_email
				+ ", customer_phone=" + customer_phone + ", customer_ssn=" + customer_ssn + ", customer_login="
				+ customer_login + "]";
	}

}
