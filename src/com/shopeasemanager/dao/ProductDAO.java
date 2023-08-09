package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.Product;

public interface ProductDAO {

	public void addProduct(Product product);

	public void updateProduct(Product product, String selectedAttribute);

	public Product getProduct(Long productID);

	public List<Product> displayAllProducts();

}
