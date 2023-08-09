package com.shopeasemanager.entity;

public class Discount {
	private Product product;
	private double discountAmount;
	public Discount(Product product, double discountAmount) {
		super();
		this.product = product;
		this.discountAmount = discountAmount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
	
	
	
	

}
