package com.shopeasemanager.entity;

public class ProductOffer {
	
	private Long pfMappingID;
	private Product product;
	private Offer offer;
	public ProductOffer(Long pfMappingID, Product product, Offer offer) {
		super();
		this.pfMappingID = pfMappingID;
		this.product = product;
		this.offer = offer;
	}
	
	
	
	public ProductOffer(Product product, Offer offer) {
		super();
		this.product = product;
		this.offer = offer;
	}



	public Long getPfMappingID() {
		return pfMappingID;
	}
	public void setPfMappingID(Long pfMappingID) {
		this.pfMappingID = pfMappingID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	
	
	
	

}
