package com.shopeasemanager.entity;

public class Product {

	private Long productID;
	private String productName;
	private String productBrand;
	private Double productRate;
	private Double productGst;
	public Product(Long productID, String productName, String productBrand, Double productRate, Double productGst) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productRate = productRate;
		this.productGst = productGst;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long productID) {
		super();
		this.productID = productID;
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
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public Double getProductRate() {
		return productRate;
	}
	public void setProductRate(Double productRate) {
		this.productRate = productRate;
	}
	public Double getProductGst() {
		return productGst;
	}
	public void setProductGst(Double productGst) {
		this.productGst = productGst;
	}



}
