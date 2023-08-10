package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.ProductOffer;

public interface ProductOfferDAO {
	
	
	public ProductOffer getProductOffer(Long productId);
	
	
	public Boolean addProductOffer(ProductOffer productOffer);
	
	
	public Boolean updateProductOffer(ProductOffer productOffer);
	
	public List<ProductOffer> displayProductOffer();
	
	
	

}
