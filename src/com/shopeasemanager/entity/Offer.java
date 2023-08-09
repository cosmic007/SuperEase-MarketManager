package com.shopeasemanager.entity;

public class Offer {
	
	private Long offerID;
	private String description;
	public Offer(Long offerID, String description) {
		super();
		this.offerID = offerID;
		this.description = description;
	}
	public Long getOfferID() {
		return offerID;
	}
	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}
