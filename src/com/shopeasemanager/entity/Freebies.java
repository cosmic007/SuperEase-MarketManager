package com.shopeasemanager.entity;

public class Freebies {
	
	private Bill bill;
	private Product product;
	private Long quantity;
	private Customer customer;
	public Freebies(Bill bill, Product product, Long quantity, Customer customer) {
		super();
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.customer = customer;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
