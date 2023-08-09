package com.shopeasemanager.service;

import com.shopeasemanager.dao.PurchaseStatsDAOImpl;

public class PurchaseStatsServiceImpl implements PurchaseStatsService {
	PurchaseStatsDAOImpl purchaseStatsDAOImpl = new PurchaseStatsDAOImpl();

	@Override
	public void updatePurchaseCount(Long product_id, Long quantity) {
		
		purchaseStatsDAOImpl.updatePurchaseCount(product_id,quantity);
		
		
	}

	@Override
	public void incrementPurchaseCount(Long product_id) {
		purchaseStatsDAOImpl.incrementPurchaseCount(product_id);
		
	}

}
