package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.ProductCategory;

public interface ProductCategoryService {
	public ProductCategory getCategory(String categoryID);
	
	public void addProductCategory(ProductCategory productCategory);
	
	public List<ProductCategory> displayProductCatgeory();
	
	public void updateProductCategory(String categoryCode,String categoryName);

}
