package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;

public class PurchaseStatsDAOImpl implements PurchaseStatsDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void updatePurchaseCount(Long product_id, Long quantity) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="update purchase_stats set purchase_count=? where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, quantity);
			preparedStatement.setLong(2,product_id);
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void incrementPurchaseCount(Long product_id) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="update purchase_stats set purchase_count=purchase_count+1 where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1,product_id);
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}


}
