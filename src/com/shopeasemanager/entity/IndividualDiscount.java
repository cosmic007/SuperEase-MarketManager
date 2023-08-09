package com.shopeasemanager.entity;

public class IndividualDiscount {
	private Customer customer;
	private Product product;
	private Double discountAmount;
	public IndividualDiscount(Customer customer, Product product, Double discountAmount) {
		super();
		this.customer = customer;
		this.product = product;
		this.discountAmount = discountAmount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
	

}
