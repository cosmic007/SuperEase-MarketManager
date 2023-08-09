package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.Stock;

public interface StockDAO {
	
	
	
	public boolean checkIfStockEmpty(Long product_id);
	
	public Long getStockByID(Long  productID);
	
	public void setStockByID(Long productID,Long count);
	
	public void decrementStockbyId(Long productID,Long count);
	
	public List<Stock> getStockList();
	
	public List<Stock> getEmptyStockList();
	
	

}
