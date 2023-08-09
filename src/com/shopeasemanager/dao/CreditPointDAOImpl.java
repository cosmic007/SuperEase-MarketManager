package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.CreditPoint;
import com.shopeasemanager.entity.ProductCategory;

public class CreditPointDAOImpl implements CreditPointDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();
	

	@Override
	public Long getCreditPoint(Long productID) {
		Long creditPoint=null;
		
		try {
			Connection connection=ds.getConnection();
			String sqlQuery ="select credit_point from pcmapping join creditpoint on pcmapping.category_code=creditpoint.category_code where product_id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, productID);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				creditPoint=resultSet.getLong(1);
			}
			connection.close();
			
			
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return creditPoint;
	}


	@Override
	public List<CreditPoint> displayCreditPoint() {
		List <CreditPoint> creditPointList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from creditpoint";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				ProductCategory category  = new ProductCategory();
				category.setCategoryID(resultSet.getString(1));		
				CreditPoint creditPoint = new CreditPoint(category,resultSet.getLong(2));
				creditPointList.add(creditPoint);
			}
			connection.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
		return creditPointList;
	}


	@Override
	public void updateCreditPoint(String categoryCode, Long creditPoint) {
		
		
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery = "update creditpoint set credit_point=? where category_code=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, creditPoint);
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
