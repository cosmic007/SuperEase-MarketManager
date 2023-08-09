package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.Stock;

public class StockDAOImpl implements StockDAO {
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public boolean checkIfStockEmpty(Long product_id) {
		boolean isEmpty=false;
		try {
			Connection connection = ds.getConnection();
			String sqlGetCountQuery = "select available_stock from stock where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlGetCountQuery);
			preparedStatement.setLong(1, product_id);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Long availableStock=resultSet.getLong(1);
				if(availableStock==0)
				{
					isEmpty=true;
					
				}
				
				
			}
			connection.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return isEmpty;
	}

	@Override
	public Long getStockByID(Long productID) {
		Long availableStock=null;
		try
		{
			Connection connection = ds.getConnection();
			String sqlGetStockCount="select available_stock from stock where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlGetStockCount);
			preparedStatement.setLong(1, productID);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				availableStock=resultSet.getLong(1);
				
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return availableStock;
	}

	@Override
	public void setStockByID(Long productID, Long count) {
		
		try
		{
			Connection connection=ds.getConnection();
			Long currentAvailableStock=getStockByID(productID);
			String sqlSetStockCount="update stock set available_stock =? where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSetStockCount);
			Long newCurrentStock=currentAvailableStock+count;
			preparedStatement.setLong(1, newCurrentStock);
			preparedStatement.setLong(2,productID);
			preparedStatement.executeUpdate();
			
			connection.close();
		
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public void decrementStockbyId(Long productID, Long count) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="update stock set available_stock=? where product_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			Long currentAvailableStock=getStockByID(productID);
	
			if(currentAvailableStock>=count)
			{
				Long newCurrentStock = currentAvailableStock-count;
				preparedStatement.setLong(1, newCurrentStock);
				preparedStatement.setLong(2, productID);
				preparedStatement.executeUpdate();
				
			}
			else
			{
				System.out.println("CurrentAvailable Stock is less then the decremented count");
			}
			
			
			
			
			connection.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public List<Stock> getStockList() {
		List <Stock> stockList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlGetStock="select * from stock";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlGetStock);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Product product = new Product(resultSet.getLong(1));
				Stock stock = new Stock(product,resultSet.getLong(2));
				stockList.add(stock);
				
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	
		return stockList ;
	}

	@Override
	public List<Stock> getEmptyStockList() {
		List <Stock> stockList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlGetStock="select * from stock where available_stock=?";
			long count=0;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlGetStock);
			preparedStatement.setLong(1, count);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Product product = new Product(resultSet.getLong(1));
				Stock stock = new Stock(product,resultSet.getLong(2));
				stockList.add(stock);
				
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	
		return stockList ;
	}

}
