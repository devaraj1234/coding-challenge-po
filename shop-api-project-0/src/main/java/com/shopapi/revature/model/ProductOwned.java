package com.shopapi.revature.model;

import java.util.Date;

public class ProductOwned {

	private int order_no;
	private Product product_owned;
	private Customer product_owner;
	private int owned_quantity;
	private Date owned_date;
	private String owned_status;

	public ProductOwned() {
	}

	public ProductOwned(int order_no, Product product_owned, Customer product_owner, int owned_quantity,
			Date owned_date, String owned_status) {
		super();
		this.order_no = order_no;
		this.product_owned = product_owned;
		this.product_owner = product_owner;
		this.owned_quantity = owned_quantity;
		this.owned_date = owned_date;
		this.owned_status = owned_status;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public Product getProduct_owned() {
		return product_owned;
	}

	public void setProduct_owned(Product product_owned) {
		this.product_owned = product_owned;
	}

	public Customer getProduct_owner() {
		return product_owner;
	}

	public void setProduct_owner(Customer product_owner) {
		this.product_owner = product_owner;
	}

	public int getOwned_quantity() {
		return owned_quantity;
	}

	public void setOwned_quantity(int owned_quantity) {
		this.owned_quantity = owned_quantity;
	}

	public Date getOwned_date() {
		return owned_date;
	}

	public void setOwned_date(Date owned_date) {
		this.owned_date = owned_date;
	}

	public String getOwned_status() {
		return owned_status;
	}

	public void setOwned_status(String owned_status) {
		this.owned_status = owned_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order_no;
		result = prime * result + ((owned_date == null) ? 0 : owned_date.hashCode());
		result = prime * result + owned_quantity;
		result = prime * result + ((owned_status == null) ? 0 : owned_status.hashCode());
		result = prime * result + ((product_owned == null) ? 0 : product_owned.hashCode());
		result = prime * result + ((product_owner == null) ? 0 : product_owner.hashCode());
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
		ProductOwned other = (ProductOwned) obj;
		if (order_no != other.order_no)
			return false;
		if (owned_date == null) {
			if (other.owned_date != null)
				return false;
		} else if (!owned_date.equals(other.owned_date))
			return false;
		if (owned_quantity != other.owned_quantity)
			return false;
		if (owned_status == null) {
			if (other.owned_status != null)
				return false;
		} else if (!owned_status.equals(other.owned_status))
			return false;
		if (product_owned == null) {
			if (other.product_owned != null)
				return false;
		} else if (!product_owned.equals(other.product_owned))
			return false;
		if (product_owner == null) {
			if (other.product_owner != null)
				return false;
		} else if (!product_owner.equals(other.product_owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product_Owner [order_no=" + order_no + ", product_owned=" + product_owned + ", product_owner="
				+ product_owner + ", owned_quantity=" + owned_quantity + ", owned_date=" + owned_date
				+ ", owned_status=" + owned_status + "]";
	}

}
