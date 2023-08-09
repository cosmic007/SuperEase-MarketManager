package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.ProductCategoryDAOImpl;
import com.shopeasemanager.entity.ProductCategory;

public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	ProductCategoryDAOImpl productCategoryDAOImpl =new ProductCategoryDAOImpl();

	@Override
	public ProductCategory getCategory(String categoryID) {
		
		
		
		// TODO Auto-generated method stub
		return productCategoryDAOImpl.getCategory(categoryID);
	}

	@Override
	public void addProductCategory(ProductCategory productCategory) {
		
		productCategoryDAOImpl.addProductCategory(productCategory);
	}

	@Override
	public List<ProductCategory> displayProductCatgeory() {
		
		return productCategoryDAOImpl.displayProductCatgeory();
	}

	@Override
	public void updateProductCategory(String categoryCode, String categoryName) {
		
		productCategoryDAOImpl.updateProductCategory(categoryCode, categoryName);
		
	}

}
