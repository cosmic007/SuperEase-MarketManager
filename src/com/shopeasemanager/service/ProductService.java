package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Product;

public interface ProductService {
	public void addProduct(Product product);
	
	public void updateProduct(Product product,String selectedAttribute);
	public List<Product> displayAllProducts();
	public Product getProduct(Long productID);

}
