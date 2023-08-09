package com.shopeasemanager.entity;

public class PCMapping {
	
	private Long mappingID;
	private Product product;
	private ProductCategory productCategory;
	public PCMapping(Long mappingID, Product product, ProductCategory productCategory) {
		super();
		this.mappingID = mappingID;
		this.product = product;
		this.productCategory = productCategory;
	}
	public Long getMappingID() {
		return mappingID;
	}
	public void setMappingID(Long mappingID) {
		this.mappingID = mappingID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	
	
	

}
