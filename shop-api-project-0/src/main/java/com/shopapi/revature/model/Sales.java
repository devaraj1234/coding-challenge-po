package com.shopapi.revature.model;

import java.util.Date;

public class Sales {

	private Integer order_no;
	private Product product;
	private Customer customer;
	private int sales_quantity;
	private double price_per_unit;
	private Date sales_date;
	private String sales_status;

	public Sales() {
	}
	
	public Sales(Integer order_no, Customer customer, Product product) {
		this.order_no = order_no;
		this.customer = customer;
		this.product = product;
	}
	
	public Sales(Integer order_no, Customer customer) {
		this.order_no = order_no;
		this.customer = customer;
	}

	public Sales(Integer order_no, Product product, Customer customer, int sales_quantity,
			double price_per_unit, Date sales_date, String sales_status) {
		super();
		this.order_no = order_no;
		this.product = product;
		this.customer = customer;
		this.sales_quantity = sales_quantity;
		this.price_per_unit = price_per_unit;
		this.sales_date = sales_date;
		this.sales_status = sales_status;
	}

	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getSales_quantity() {
		return sales_quantity;
	}

	public void setSales_quantity(int sales_quantity) {
		this.sales_quantity = sales_quantity;
	}
	
	public double getPrice_Per_Unit() {
		return price_per_unit;
	}

	public void setPrice_Per_Unit(double price_per_unit) {
		this.price_per_unit = price_per_unit;
	}

	public Date getSales_date() {
		return sales_date;
	}

	public void setSales_date(Date sales_date) {
		this.sales_date = sales_date;
	}

	public String getSales_status() {
		return sales_status;
	}

	public void setSales_status(String sales_status) {
		this.sales_status = sales_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order_no;
		result = prime * result + ((sales_date == null) ? 0 : sales_date.hashCode());
		result = prime * result + sales_quantity;
		result = prime * result + ((sales_status == null) ? 0 : sales_status.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		Sales other = (Sales) obj;
		if (order_no != other.order_no)
			return false;
		if (sales_date == null) {
			if (other.sales_date != null)
				return false;
		} else if (!sales_date.equals(other.sales_date))
			return false;
		if (sales_quantity != other.sales_quantity)
			return false;
		if (sales_status == null) {
			if (other.sales_status != null)
				return false;
		} else if (!sales_status.equals(other.sales_status))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "customer [order_no=" + order_no + ", product=" + product + ", customer="
				+ customer + ", sales_quantity=" + sales_quantity + ", sales_date=" + sales_date
				+ ", sales_status=" + sales_status + "]";
	}

}
