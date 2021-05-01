package com.shopapi.revature.model;

public class LoginDetails {

	private Integer login_id;
	private String login_user;
	private String login_password;
	private User user_role;

	public LoginDetails() {
	}

	public LoginDetails(Integer login_id, String login_user, String login_password, User user_role) {
		super();
		this.login_id = login_id;
		this.login_user = login_user;
		this.login_password = login_password;
		this.user_role = user_role;
	}

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public String getLogin_user() {
		return login_user;
	}

	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public User getUser_role() {
		return user_role;
	}

	public void setUser_role(User user_role) {
		this.user_role = user_role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + login_id;
		result = prime * result + ((login_password == null) ? 0 : login_password.hashCode());
		result = prime * result + ((login_user == null) ? 0 : login_user.hashCode());
		result = prime * result + ((user_role == null) ? 0 : user_role.hashCode());
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
		LoginDetails other = (LoginDetails) obj;
		if (login_id != other.login_id)
			return false;
		if (login_password == null) {
			if (other.login_password != null)
				return false;
		} else if (!login_password.equals(other.login_password))
			return false;
		if (login_user == null) {
			if (other.login_user != null)
				return false;
		} else if (!login_user.equals(other.login_user))
			return false;
		if (user_role == null) {
			if (other.user_role != null)
				return false;
		} else if (!user_role.equals(other.user_role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginDetails [login_id=" + login_id + ", login_user=" + login_user + ", login_password="
				+ login_password + ", user_role=" + user_role + "]";
	}
}
