package com.shopapi.revature.model;

import java.util.Date;

public class WeeklyCollection {
	
	private Date week_Start_Date;
	private Integer week_number;
	private double weekly_collection;
	
	public WeeklyCollection() {}

	public WeeklyCollection(Date week_Start_Date, Integer week_number, double weekly_collection) {
		super();
		this.week_Start_Date = week_Start_Date;
		this.week_number = week_number;
		this.weekly_collection = weekly_collection;
	}

	public Date getWeek_Start_Date() {
		return week_Start_Date;
	}

	public void setWeek_Start_Date(Date week_Start_Date) {
		this.week_Start_Date = week_Start_Date;
	}

	public Integer getWeek_number() {
		return week_number;
	}

	public void setWeek_number(Integer week_number) {
		this.week_number = week_number;
	}

	public double getWeekly_collection() {
		return weekly_collection;
	}

	public void setWeekly_collection(double weekly_collection) {
		this.weekly_collection = weekly_collection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((week_Start_Date == null) ? 0 : week_Start_Date.hashCode());
		result = prime * result + ((week_number == null) ? 0 : week_number.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weekly_collection);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		WeeklyCollection other = (WeeklyCollection) obj;
		if (week_Start_Date == null) {
			if (other.week_Start_Date != null)
				return false;
		} else if (!week_Start_Date.equals(other.week_Start_Date))
			return false;
		if (week_number == null) {
			if (other.week_number != null)
				return false;
		} else if (!week_number.equals(other.week_number))
			return false;
		if (Double.doubleToLongBits(weekly_collection) != Double.doubleToLongBits(other.weekly_collection))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeeklyCollection [week_start_date: " + week_Start_Date + ", week_number: " + week_number
				+ ", weekly_collection: " + weekly_collection + "]";
	}
	
	

}
