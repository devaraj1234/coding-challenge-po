package com.ersproject.model;

public class ReimbursementType {

	private Integer reim_type_id;
	private String reim_type;

	public ReimbursementType() {
	}

	public ReimbursementType(Integer reim_type_id, String reim_type) {
		super();
		this.reim_type_id = reim_type_id;
		this.reim_type = reim_type;
	}

	public Integer getReim_type_id() {
		return reim_type_id;
	}

	public void setReim_type_id(Integer reim_type_id) {
		this.reim_type_id = reim_type_id;
	}

	public String getReim_type() {
		return reim_type;
	}

	public void setReim_type(String reim_type) {
		this.reim_type = reim_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reim_type == null) ? 0 : reim_type.hashCode());
		result = prime * result + ((reim_type_id == null) ? 0 : reim_type_id.hashCode());
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
		ReimbursementType other = (ReimbursementType) obj;
		if (reim_type == null) {
			if (other.reim_type != null)
				return false;
		} else if (!reim_type.equals(other.reim_type))
			return false;
		if (reim_type_id == null) {
			if (other.reim_type_id != null)
				return false;
		} else if (!reim_type_id.equals(other.reim_type_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementType [reim_type_id=" + reim_type_id + ", reim_type="
				+ reim_type + "]";
	}
}
