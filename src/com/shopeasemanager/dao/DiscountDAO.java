package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.Discount;
import com.shopeasemanager.entity.Product;

public interface DiscountDAO {
	
	public Double getDiscount(Product product);
	
	public List<Discount> displayAllDiscount();
	
	public void updateDiscount(Long productID,double discountAmount);
	
	

}
