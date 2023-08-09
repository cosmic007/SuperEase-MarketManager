package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.ProductDAOImpl;
import com.shopeasemanager.entity.Product;

public class ProductServiceImpl implements ProductService {
	ProductDAOImpl productDAOImpl = new ProductDAOImpl();
	@Override
	public void addProduct(Product product) {
		productDAOImpl.addProduct(product);
		
	}
	@Override
	public void updateProduct(Product product, String selectedAttribute) {
		productDAOImpl.updateProduct(product, selectedAttribute);
		
	}
	@Override
	public List<Product> displayAllProducts() {
		
		return productDAOImpl.displayAllProducts();
	}
	@Override
	public Product getProduct(Long productID) {
		
	
		return productDAOImpl.getProduct(productID);
	}

}
