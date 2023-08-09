package com.shopeasemanager.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.BillDetails;
import com.shopeasemanager.entity.Customer;
import com.shopeasemanager.entity.Employee;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.service.CustomerServiceImpl;
import com.shopeasemanager.service.EmployeeServiceImpl;
import com.shopeasemanager.service.PurchaseStatsServiceImpl;
import com.shopeasemanager.service.StockServiceImpl;

public class BillDAOImpl implements BillDAO {

	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public boolean createBill(Bill bill) {
		Boolean success=false;
		try
		{
			Connection connection=ds.getConnection();
			String sqlQuery="insert into bill(date_0f_purchase,customer_id,employee_id) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = bill.getDateOfPurchase().format(formatter);
			
			preparedStatement.setString(1, formattedDateTime);
			preparedStatement.setString(2, bill.getCustomer().getCustomerID());
			preparedStatement.setString(3, bill.getEmployee().getEmployeeID());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				System.out.println("Bill Created");
				success=true;
			}
			else
			{
				System.out.println("Failed to create bill");
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
	public Long getBillNoByDOP(LocalDateTime dateOfPurchase) {
		Long billno=null;
		
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select billno from bill where date_0f_purchase=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime=dateOfPurchase.format(formatter);
			System.out.println(formattedDateTime);
			preparedStatement.setString(1, formattedDateTime);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				billno=resultSet.getLong(1);
			}
			connection.close();			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return billno;
	}

	@Override
	public boolean generateBill(List<BillDetails> billDetailsList) {
		StockServiceImpl stockServiceImpl = new StockServiceImpl();
		PurchaseStatsServiceImpl purchaseStatsServiceImpl = new PurchaseStatsServiceImpl();
		boolean success=false;
		try
		{
			boolean done=false;
			Connection connection = ds.getConnection();
			String sqlQuery="insert into billdetails(billno,product_id,quantity,discount_earned,credit_earned) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			for(BillDetails billDetails:billDetailsList)
			{
				preparedStatement.setLong(1,billDetails.getBill().getBillNo());
				preparedStatement.setLong(2, billDetails.getProduct().getProductID());
				preparedStatement.setLong(3, billDetails.getQuantity());
				preparedStatement.setDouble(4, billDetails.getDiscountEarned());
				preparedStatement.setLong(5, billDetails.getCreditEarned());
				int result=preparedStatement.executeUpdate();
				if(result!=0)
				{
					stockServiceImpl.decrementStockbyId(billDetails.getProduct().getProductID(), billDetails.getQuantity());
					purchaseStatsServiceImpl.incrementPurchaseCount(billDetails.getProduct().getProductID());
				}
				done=true;
				
				
				
			}
			connection.close();
			if(done=true)
			{
				success=true;
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return success;
	}

	@Override
	public List<BillDetails> getBillDetailsList(Long billNo) {
		List<BillDetails> billDetailsList = new ArrayList<>();
		
		try
		{
			
			Connection connection=ds.getConnection();
			String sqlQuery="select billno,product_id,quantity,discount_earned,credit_earned from billdetails where billno=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, billNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Bill bill = new Bill(resultSet.getLong(1));
				Product product = new Product(resultSet.getLong(2));
				BillDetails billDetails=new BillDetails(bill,product,resultSet.getLong(3),resultSet.getDouble(4),resultSet.getLong(5));
				billDetailsList.add(billDetails);
			}
			connection.close();
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		return billDetailsList;
	}

	@Override
	public boolean completeBillGeneration(Bill bill) {
		boolean success=false;
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="update bill set discount_applied=?,credit_earned=?,credit_used=?,total_rate=? where billno=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setDouble(1, bill.getDiscountApplied());
			preparedStatement.setLong(2,bill.getCreditEarned());
			preparedStatement.setLong(3, bill.getCreditUsed());
			preparedStatement.setDouble(4, bill.getTotalRate());
			preparedStatement.setLong(5, bill.getBillNo());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				success=true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return success;
	}

	@Override
	public String getCustomerFromBill(Long billNo) {
		String customerID=null;
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select customer_id from bill where billno=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, billNo);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				customerID=resultSet.getString(1);
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return customerID;
	}

	@Override
	public String getEmployeeFromBill(Long billNo) {
		String employeeID=null;
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select employee_id from bill where billno=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, billNo);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				employeeID=resultSet.getString(1);
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return employeeID;
	}

	@Override
	public Bill getBillFromBillNo(Long billNo) {
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

		
		Bill bill=null;
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery="select * from bill where billno=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, billNo);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String dateOfPurchase = resultSet.getString(2);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dOP=LocalDateTime.parse(dateOfPurchase,formatter);
				Customer customer = customerServiceImpl.getCustomer(resultSet.getString(3));
				Employee employee = employeeServiceImpl.getEmployee(resultSet.getString(4));
				
				bill=new Bill(resultSet.getLong(1),dOP,customer,employee,resultSet.getDouble(5),resultSet.getLong(6),resultSet.getLong(7),resultSet.getDouble(8));
				
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return bill;
	}

	
}
