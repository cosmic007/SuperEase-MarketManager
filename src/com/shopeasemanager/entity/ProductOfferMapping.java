package com.shopeasemanager.entity;

public class ProductOfferMapping {
	
	private Long pfMappingID;
	private Long productID;
	private Long offerID;
	public ProductOfferMapping(Long pfMappingID, Long productID, Long offerID) {
		super();
		this.pfMappingID = pfMappingID;
		this.productID = productID;
		this.offerID = offerID;
	}
	
	
	
	public ProductOfferMapping(Long productID, Long offerID) {
		super();
		this.productID = productID;
		this.offerID = offerID;
	}



	public Long getPfMappingID() {
		return pfMappingID;
	}
	public void setPfMappingID(Long pfMappingID) {
		this.pfMappingID = pfMappingID;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public Long getOfferID() {
		return offerID;
	}
	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}
	
	

}
