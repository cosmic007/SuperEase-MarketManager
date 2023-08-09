package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Discount;
import com.shopeasemanager.entity.Product;

public interface DiscountService {
	public Double getDiscount(Product product);

	public List<Discount> displayAllDiscount();

	public void updateDiscount(Long productID, double discountAmount);

}
