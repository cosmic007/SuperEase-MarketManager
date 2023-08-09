package com.shopeasemanager.service;

public interface PurchaseStatsService {
	public void updatePurchaseCount(Long product_id , Long quantity);
	public void incrementPurchaseCount(Long product_id);
	

}
