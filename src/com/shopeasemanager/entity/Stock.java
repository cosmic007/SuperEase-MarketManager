package com.shopeasemanager.entity;

public class Stock {
	private Product product;
	private Long availableStock;
	public Stock(Product product, Long availableStock) {
		super();
		this.product = product;
		this.availableStock = availableStock;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(Long availableStock) {
		this.availableStock = availableStock;
	}
	
	

}
