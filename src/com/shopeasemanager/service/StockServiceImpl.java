package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.StockDAOImpl;
import com.shopeasemanager.entity.Stock;

public class StockServiceImpl implements StockService{
	StockDAOImpl stockDAOImpl = new StockDAOImpl();

	@Override
	public boolean checkIfStockEmpty(Long product_id) {
		

		return stockDAOImpl.checkIfStockEmpty(product_id);
	}

	@Override
	public Long getStockByID(Long productID) {
		
		return stockDAOImpl.getStockByID(productID);
	}

	@Override
	public void setStockByID(Long productID, Long count) {
		stockDAOImpl.setStockByID(productID, count);
		
	}

	@Override
	public void decrementStockbyId(Long productID, Long count) {
		
		stockDAOImpl.decrementStockbyId(productID, count);
	}

	@Override
	public List<Stock> getStockList() {
		
		return stockDAOImpl.getStockList();
	}

	@Override
	public List<Stock> getEmptyStockList() {
		
		return stockDAOImpl.getEmptyStockList();
	}



}
