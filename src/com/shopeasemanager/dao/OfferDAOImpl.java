package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Offer;

public class OfferDAOImpl implements OfferDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public boolean addOfferDAO(String description) {
		
		boolean success=false;
		
		try
		{
			Connection connection=ds.getConnection();
			String sqlQuery="insert into offer(description) values (?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1,description);
			int result = preparedStatement.executeUpdate();
			if(result!=0)
			{
				success=true;
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		return success;
	
		
	}

	@Override
	public String getOffer(Long offerID) {
		String description=null;
		
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select description from offer where offer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, offerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				description=resultSet.getString(1);
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return description;
	}

	@Override
	public boolean updateOffer(Offer offer) {
		boolean success = false;
		try
		{
			Connection connection =ds.getConnection();
			String sqlQuery = "update offer set description=? where offer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1,offer.getDescription());
			preparedStatement.setLong(2,offer.getOfferID());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				success =true;
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return success;
	}

	@Override
	public List<Offer> displayOffer() {
		
		List<Offer> offerList = new ArrayList<>();
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from offer";
			PreparedStatement preparedStatement =connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Offer offer = new Offer(resultSet.getLong(1),resultSet.getString(2));
				offerList.add(offer);
			}
			connection.close(); 
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return offerList;
	}

}
