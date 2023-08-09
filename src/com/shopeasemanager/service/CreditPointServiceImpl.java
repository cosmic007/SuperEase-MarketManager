package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.CreditPointDAOImpl;
import com.shopeasemanager.entity.CreditPoint;


public class CreditPointServiceImpl implements CreditPointService {
	
	CreditPointDAOImpl creditPointDAOImpl = new CreditPointDAOImpl();

	@Override
	public Long getCreditPoint(Long productID) {

		return creditPointDAOImpl.getCreditPoint(productID);
	}

	@Override
	public List<CreditPoint> displayCreditPoint() {
		

		return creditPointDAOImpl.displayCreditPoint();
	}

	@Override
	public void updateCreditPoint(String categoryCode, Long creditPoint) {
		creditPointDAOImpl.updateCreditPoint(categoryCode, creditPoint);
		
	}


}
