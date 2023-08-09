package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.LeastSellingProduct;

public interface PurchaseStatsService {
public void updatePurchaseCount(Long product_id , Long quantity);
	
	public void incrementPurchaseCount(Long product_id);
	
	public List<LeastSellingProduct> displayLeastSellingByCateory (String categoryCode,int limit);
	
	public List<LeastSellingProduct> displayLeastSellingByRate(String value,int limit);
	
	

}
