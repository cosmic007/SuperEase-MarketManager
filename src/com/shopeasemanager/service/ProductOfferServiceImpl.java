package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.ProductOfferDAOImpl;
import com.shopeasemanager.entity.ProductOffer;

public class ProductOfferServiceImpl implements ProductOfferService{
	
	ProductOfferDAOImpl productOfferDAOImpl = new ProductOfferDAOImpl();

	@Override
	public ProductOffer getProductOffer(Long productId) {
		// TODO Auto-generated method stub
		return productOfferDAOImpl.getProductOffer(productId);
	}

	@Override
	public Boolean addProductOffer(ProductOffer productOffer) {
		// TODO Auto-generated method stub
		return productOfferDAOImpl.addProductOffer(productOffer);
	}

	@Override
	public Boolean updateProductOffer(ProductOffer productOffer) {
		// TODO Auto-generated method stub
		return productOfferDAOImpl.updateProductOffer(productOffer);
	}

	@Override
	public List<ProductOffer> displayProductOffer() {
		// TODO Auto-generated method stub
		return productOfferDAOImpl.displayProductOffer();
	}

}
