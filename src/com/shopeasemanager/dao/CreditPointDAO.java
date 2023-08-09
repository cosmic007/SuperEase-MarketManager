package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.CreditPoint;

public interface CreditPointDAO {
	
	
	public Long getCreditPoint(Long productID);
	
	public List<CreditPoint> displayCreditPoint();
	
	public void updateCreditPoint(String categoryCode, Long creditPoint);

}
