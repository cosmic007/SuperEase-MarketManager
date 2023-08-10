package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.ProductOffer;

public interface ProductOfferService {

	
	
	public ProductOffer getProductOffer(Long productId);
	
	
	public Boolean addProductOffer(ProductOffer productOffer);
	
	
	public Boolean updateProductOffer(ProductOffer productOffer);
	
	public List<ProductOffer> displayProductOffer();
	
}
