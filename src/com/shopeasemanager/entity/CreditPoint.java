package com.shopeasemanager.entity;

public class CreditPoint {
	
	private ProductCategory productCategory;
	private Long creditPoint;
	public CreditPoint(ProductCategory productCategory, Long creditPoint) {
		super();
		this.productCategory = productCategory;
		this.creditPoint = creditPoint;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public Long getCreditPoint() {
		return creditPoint;
	}
	public void setCreditPoint(Long creditPoint) {
		this.creditPoint = creditPoint;
	}
	
	
	
	


}
