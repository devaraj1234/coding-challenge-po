package com.shopapi.revature.model;

import java.util.Date;

public class OfferedMade {

	private Integer offer_no;
	private Product product;
	private Customer product_owner;
	private int offer_quantity;
	private Date offer_date;
	private double offered_price_per_unit;
	private double payment_made;
	private String offer_status;

	public OfferedMade(Integer offer_no, Product product) {
		this.offer_no = offer_no;
		this.product = product;
	}

	public OfferedMade() {
	}

	public OfferedMade(Integer offer_no, Product product, Customer product_owner, int offer_quantity, Date offer_date,
			double offered_price_per_unit, double payment_made, String offer_status) {
		super();
		this.offer_no = offer_no;
		this.product = product;
		this.product_owner = product_owner;
		this.offer_quantity = offer_quantity;
		this.offer_date = offer_date;
		this.offered_price_per_unit = offered_price_per_unit;
		this.payment_made = payment_made;
		this.offer_status = offer_status;
	}

	public Integer getOffer_no() {
		return offer_no;
	}

	public void setOffer_no(Integer offer_no) {
		this.offer_no = offer_no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getProduct_owner() {
		return product_owner;
	}

	public void setProduct_owner(Customer product_owner) {
		this.product_owner = product_owner;
	}

	public int getOffer_quantity() {
		return offer_quantity;
	}

	public void setOffer_quantity(int offer_quantity) {
		this.offer_quantity = offer_quantity;
	}

	public Date getOffer_date() {
		return offer_date;
	}

	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}

	public double getOffered_price_per_unit() {
		return offered_price_per_unit;
	}

	public void setOffered_price_per_unit(double offered_price_per_unit) {
		this.offered_price_per_unit = offered_price_per_unit;
	}

	public double getPayment_made() {
		return payment_made;
	}

	public void setPayment_made(double payment_made) {
		this.payment_made = payment_made;
	}

	public String getOffer_status() {
		return offer_status;
	}

	public void setOffer_status(String offer_status) {
		this.offer_status = offer_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offer_date == null) ? 0 : offer_date.hashCode());
		result = prime * result + ((offer_no == null) ? 0 : offer_no.hashCode());
		result = prime * result + ((offer_status == null) ? 0 : offer_status.hashCode());
		result = prime * result + offer_quantity;
		long temp;
		temp = Double.doubleToLongBits(offered_price_per_unit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(payment_made);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OfferedMade other = (OfferedMade) obj;
		if (offer_date == null) {
			if (other.offer_date != null)
				return false;
		} else if (!offer_date.equals(other.offer_date))
			return false;
		if (offer_no == null) {
			if (other.offer_no != null)
				return false;
		} else if (!offer_no.equals(other.offer_no))
			return false;
		if (offer_status == null) {
			if (other.offer_status != null)
				return false;
		} else if (!offer_status.equals(other.offer_status))
			return false;
		if (offer_quantity != other.offer_quantity)
			return false;
		if (Double.doubleToLongBits(offered_price_per_unit) != Double.doubleToLongBits(other.offered_price_per_unit))
			return false;
		if (Double.doubleToLongBits(payment_made) != Double.doubleToLongBits(other.payment_made))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
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
		return "OfferedMade [offer_no=" + offer_no + ", product=" + product + ", product_owner=" + product_owner
				+ ", offer_quantity=" + offer_quantity + ", offer_date=" + offer_date + ", offered_price_per_unit="
				+ offered_price_per_unit + ", payment_made=" + payment_made + ", offer_status=" + offer_status + "]";
	}

}