package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Discount;
import com.shopeasemanager.entity.Product;

public class DiscountDAOImpl implements DiscountDAO {
	DataSource ds = DBConnectionPool.getDataSource();
	

	@Override
	public Double getDiscount(Product product) {
		Double discountAmount=null;
		
		try {
			Connection connection = ds.getConnection();
			String sql = "select discount_amount from discount where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, product.getProductID());
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				discountAmount=resultSet.getDouble(1);
			}
			connection.close();
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return discountAmount;
	}


	@Override
	public List<Discount> displayAllDiscount() {
		List <Discount> discountList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select * from discount";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Product product = new Product(resultSet.getLong(2));
				Discount discount = new Discount(product,resultSet.getDouble(3));
				discountList.add(discount);
				
			}
			connection.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		return discountList;
	}




	@Override
	public void updateDiscount(Long productID, double discountAmount) {
		
		try
		{
			Connection connection =ds.getConnection();
			String sqlQuery="update discount set discount_amount=? where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setDouble(1,discountAmount);
			preparedStatement.setLong(2, productID);
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
