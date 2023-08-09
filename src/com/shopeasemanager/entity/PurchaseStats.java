package com.shopeasemanager.entity;

public class PurchaseStats {
	private Product product;
	private Long purchaseCount;
	public PurchaseStats(Product product, Long purchaseCount) {
		super();
		this.product = product;
		this.purchaseCount = purchaseCount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(Long purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	
	

}
