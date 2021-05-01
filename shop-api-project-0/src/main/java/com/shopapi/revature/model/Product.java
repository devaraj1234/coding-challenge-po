package com.shopapi.revature.model;

public class Product {

	private Integer product_id;
	private String product_name;
	private int product_quantity;
	private double expected_price_per_unit;
	private String product_description;

	public Product() {
	}
	
	public Product(Integer product_id) {
		this.product_id = product_id;
	}

	public Product(Integer product_id, String product_name, int product_quantity, double expected_price_per_unit,
			String product_description) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_quantity = product_quantity;
		this.expected_price_per_unit = expected_price_per_unit;
		this.product_description = product_description;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public double getexpected_price_per_unit() {
		return expected_price_per_unit;
	}

	public void setexpected_price_per_unit(double expected_price_per_unit) {
		this.expected_price_per_unit = expected_price_per_unit;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_description == null) ? 0 : product_description.hashCode());
		result = prime * result + product_id;
		long temp;
		temp = Double.doubleToLongBits(expected_price_per_unit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		result = prime * result + product_quantity;
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
		Product other = (Product) obj;
		if (product_description == null) {
			if (other.product_description != null)
				return false;
		} else if (!product_description.equals(other.product_description))
			return false;
		if (product_id != other.product_id)
			return false;
		if (Double.doubleToLongBits(expected_price_per_unit) != Double.doubleToLongBits(other.expected_price_per_unit))
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		if (product_quantity != other.product_quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_quantity="
				+ product_quantity + ", expected_price_per_unit=" + expected_price_per_unit + ", product_description="
				+ product_description + "]";
	}

}
