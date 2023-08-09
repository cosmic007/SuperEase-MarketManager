package com.shopeasemanager.entity;

public class LeastSellingProduct {
	
	private Long productID;
	private String productName;
	private Double productRate;
	private String categoryName;
	private Integer purchaseCount;
	public LeastSellingProduct(Long productID, String productName, Double productRate, String categoryName,
			Integer purchaseCount) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productRate = productRate;
		this.categoryName = categoryName;
		this.purchaseCount = purchaseCount;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductRate() {
		return productRate;
	}
	public void setProductRate(Double productRate) {
		this.productRate = productRate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	
	
	
	

}
