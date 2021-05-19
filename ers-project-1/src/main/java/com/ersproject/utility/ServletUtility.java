package com.ersproject.utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ersproject.model.User;
import com.ersproject.model.UserRoles;

public class ServletUtility {

	public double convertStringToDouble(String stringValue) {
		return Double.parseDouble(stringValue);
	}

	public Date convertStringToDate(String stringDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long long_date = format.parse(stringDate).getTime();
		return new java.sql.Date(long_date);
	}

	public User getUpdatedUser(String new_first_name, String new_last_name, String new_email, User user) {

		String update_f_name, updated_l_name, update_email;

		if (new_first_name != null && new_first_name != "") {
			update_f_name = new_first_name;
		} else {
			update_f_name = user.getUser_first_name();
		}

		if (new_last_name != null && new_last_name != "") {
			updated_l_name = new_last_name;
		} else {
			updated_l_name = user.getUser_last_name();
		}

		if (new_email != null && new_email != "") {
			update_email = new_email;
		} else {
			update_email = user.getUser_email();
		}

		return new User(null, user.getUser_name(), user.getUser_password(), update_f_name, updated_l_name, update_email,
				new UserRoles());
	}

}
