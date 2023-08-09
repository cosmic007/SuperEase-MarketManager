package com.shopeasemanager.dao;

public interface PurchaseStatsDAO {
	
	public void updatePurchaseCount(Long product_id , Long quantity);
	
	public void incrementPurchaseCount(Long product_id);
	

	
	

}
