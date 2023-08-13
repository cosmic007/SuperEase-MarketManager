package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.Customer;
import com.shopeasemanager.entity.Freebies;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.service.ProductServiceImpl;
import com.shopeasemanager.service.StockServiceImpl;

public class FreebiesDAOImpl implements FreebiesDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void sellFreeProducts(Freebies freebies) {
		
		try
		{
			
			Connection connection = ds.getConnection();
			String sqlQuery="insert into freebies values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, freebies.getBill().getBillNo());
			preparedStatement.setLong(2, freebies.getProduct().getProductID());
			preparedStatement.setLong(3, freebies.getQuantity());
			preparedStatement.setString(4, freebies.getCustomer().getCustomerID());
			int result=preparedStatement.executeUpdate();
			
			if(result!=0)
			{
				StockServiceImpl stockServiceImpl = new StockServiceImpl();
				stockServiceImpl.decrementStockbyId(freebies.getProduct().getProductID(), freebies.getQuantity());
				
			}
			
			
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public List<Freebies> displayFreebies(Long billno) {
		
		List<Freebies> freebiesList = new ArrayList<>();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		try
		{
			
			Connection connection = ds.getConnection();
			String sqlQuery="select * from freebies where billno=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, billno);
			
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Bill bill = new Bill();
				bill.setBillNo(resultSet.getLong(1));
				Product product = productServiceImpl.getProduct(resultSet.getLong(2));
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString(4));
				Freebies freebies = new Freebies(bill,product,resultSet.getLong(3),customer);
				freebiesList.add(freebies);
			}
			
			
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return freebiesList;
	}

}
