package com.shopeasemanager.entity;

public class BillDetails {
	
	private Long billDetailsID;
	private Bill bill;
	private Product product;
	private Long quantity;
	private Double discountEarned;
	private Long creditEarned;

	public BillDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public BillDetails(Bill bill, Product product, Long quantity, Double discountEarned, Long creditEarned) {
		super();
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.discountEarned = discountEarned;
		this.creditEarned = creditEarned;
	}
	public Long getBillDetailsID() {
		return billDetailsID;
	}
	public void setBillDetailsID(Long billDetailsID) {
		this.billDetailsID = billDetailsID;
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
	public Double getDiscountEarned() {
		return discountEarned;
	}
	public void setDiscountEarned(Double discountEarned) {
		this.discountEarned = discountEarned;
	}
	public Long getCreditEarned() {
		return creditEarned;
	}
	public void setCreditEarned(Long creditEarned) {
		this.creditEarned = creditEarned;
	}
	
	

}
