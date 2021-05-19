package com.ersproject.model;

public class ReimbursementStatus {

	private Integer reim_status_id;
	private String reimb_status;

	public ReimbursementStatus() {
	}

	public ReimbursementStatus(Integer reim_status_id, String reimb_status) {
		super();
		this.reim_status_id = reim_status_id;
		this.reimb_status = reimb_status;
	}

	public Integer getReim_status_id() {
		return reim_status_id;
	}

	public void setReim_status_id(Integer reim_status_id) {
		this.reim_status_id = reim_status_id;
	}

	public String getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reim_status_id == null) ? 0 : reim_status_id.hashCode());
		result = prime * result + ((reimb_status == null) ? 0 : reimb_status.hashCode());
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (reim_status_id == null) {
			if (other.reim_status_id != null)
				return false;
		} else if (!reim_status_id.equals(other.reim_status_id))
			return false;
		if (reimb_status == null) {
			if (other.reimb_status != null)
				return false;
		} else if (!reimb_status.equals(other.reimb_status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reim_status_id=" + reim_status_id + ", reimb_status=" + reimb_status + "]";
	}
}
