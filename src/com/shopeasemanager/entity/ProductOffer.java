package com.shopeasemanager.entity;

import java.time.LocalDate;

public class ProductOffer {
	
	private Long pfMappingID;
	private Product product;
	private Offer offer;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	
	
	public ProductOffer(Product product, Offer offer) {
		super();
		this.product = product;
		this.offer = offer;
	}


	
	public ProductOffer(Product product, Offer offer, LocalDate startDate, LocalDate endDate) {
		super();
		this.product = product;
		this.offer = offer;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public ProductOffer(Long pfMappingID, Product product, Offer offer, LocalDate startDate, LocalDate endDate,
			String status) {
		super();
		this.pfMappingID = pfMappingID;
		this.product = product;
		this.offer = offer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
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


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
	
	

}
