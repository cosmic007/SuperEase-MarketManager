package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.OfferDAOImpl;
import com.shopeasemanager.entity.Offer;

public class OfferServiceImpl implements OfferService {
	
	
	OfferDAOImpl offerDAOImpl = new OfferDAOImpl();

	@Override
	public boolean addOfferDAO(String description) {
		
		return offerDAOImpl.addOfferDAO(description);
	}

	@Override
	public String getOffer(Long offerID) {
		
		return offerDAOImpl.getOffer(offerID);
	}

	@Override
	public boolean updateOffer(Offer offer) {
		
		return offerDAOImpl.updateOffer(offer);
	}

	@Override
	public List<Offer> displayOffer() {

		return offerDAOImpl.displayOffer();
	}

}
