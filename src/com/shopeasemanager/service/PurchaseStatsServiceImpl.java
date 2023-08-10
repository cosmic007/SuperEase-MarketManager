package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.PurchaseStatsDAOImpl;
import com.shopeasemanager.entity.LeastSellingProduct;

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

	@Override
	public List<LeastSellingProduct> displayLeastSellingByCateory(String categoryCode, int limit) {
		
		return purchaseStatsDAOImpl.displayLeastSellingByCateory(categoryCode, limit);
	}


}
