package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.DiscountDAOImpl;
import com.shopeasemanager.entity.Discount;
import com.shopeasemanager.entity.Product;

public class DiscountServiceImpl implements DiscountService {
	DiscountDAOImpl discountDAOImpl = new DiscountDAOImpl();

	@Override
	public Double getDiscount(Product product) {

		return discountDAOImpl.getDiscount(product);
	}

	@Override
	public List<Discount> displayAllDiscount() {

		return discountDAOImpl.displayAllDiscount();
	}

	@Override
	public void updateDiscount(Long productID, double discountAmount) {
		discountDAOImpl.updateDiscount(productID, discountAmount);

	}

}
