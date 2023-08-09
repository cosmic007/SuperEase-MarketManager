package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Offer;

public interface OfferService {
	
public boolean addOfferDAO(String description);
	
	public String getOffer(Long offerID);
	
	public boolean updateOffer(Offer offer);
	
	public List<Offer> displayOffer();

}
