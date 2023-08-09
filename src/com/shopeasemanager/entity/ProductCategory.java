package com.shopeasemanager.entity;

public class ProductCategory {
	private String categoryID;
	private String categoryName;
	public ProductCategory(String categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ProductCategory() {
		super();
		
	}
	
	
	
	
	
	
	

}
