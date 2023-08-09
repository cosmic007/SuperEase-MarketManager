package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.ProductCategory;

public class ProductCategoryDAOImpl implements ProductCategoryDAO{
	DataSource ds = DBConnectionPool.getDataSource();


	@Override
	public ProductCategory getCategory(String categoryID) {
		ProductCategory productCategory = null;
		
		try
		{
			Connection connection =ds.getConnection();
			String sqlQuery="select * from productcategory where category_code=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, categoryID);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				productCategory = new ProductCategory(resultSet.getString(1),resultSet.getString(2));
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return productCategory;
	}


	@Override
	public void addProductCategory(ProductCategory productCategory) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="insert into productcategory values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1,productCategory.getCategoryID());
			preparedStatement.setString(2, productCategory.getCategoryName());
			preparedStatement.executeUpdate();
			connection.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
	}


	@Override
	public List<ProductCategory> displayProductCatgeory() {
		List <ProductCategory> categoryList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select * from productcategory";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				ProductCategory productCategory = new ProductCategory(resultSet.getString(1),resultSet.getString(2));
				categoryList.add(productCategory);
			}
			connection.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return categoryList;
	}


	@Override
	public void updateProductCategory(String categoryCode,String categoryName) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery = "update productcategory set category_name=? where category_code=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, categoryName);
			preparedStatement.setString(2, categoryCode);
			preparedStatement.executeUpdate();		
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		
		
	}
	
	

}
