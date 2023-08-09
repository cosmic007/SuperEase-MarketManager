package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;

import com.shopeasemanager.entity.Customer;


public class CustomerDAOImpl implements CustomerDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void addCustomer(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, customer.getCustomerID());
			preparedStatement.setString(2, customer.getCustomerFirstName());
			preparedStatement.setString(3, customer.getCustomerLastName());
			Date dob= Date.valueOf(customer.getCustomerDOB());
			preparedStatement.setDate(4, dob);
			preparedStatement.setString(5, customer.getCustomerEmail());
			preparedStatement.setLong(6, customer.getCustomerPhNo());
			preparedStatement.setString(7, customer.getCustomerAddress());
			preparedStatement.setLong(8, customer.getCustomerCreditPoints());
			
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				System.out.println("Failed to Add Customer");
			} else {
				System.out.println("Customer Added Successfully");
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

	@Override
	public void updateCustomer(Customer customer, String selectedCustomer) {
		try {

			Connection connection = ds.getConnection();
			String sqlQuery = "update customer set " + selectedCustomer + " =? where customer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			switch (selectedCustomer) {
			case "customer_firstname":
				preparedStatement.setString(1, customer.getCustomerFirstName());
				break;
			case "customer_lastname":
				preparedStatement.setString(1, customer.getCustomerLastName());
				break;
			case "customer_dob":
				String dob = String.valueOf(customer.getCustomerDOB());       
				preparedStatement.setString(1, dob);
				break;
			case "customer_email":
				preparedStatement.setString(1, customer.getCustomerEmail());
				
				break;
			case "customer_phno":
				preparedStatement.setLong(1, customer.getCustomerPhNo());
				break;
			case "customer_address":
				preparedStatement.setString(1, customer.getCustomerAddress());
				break;
			case "customer_credit_points":
				preparedStatement.setLong(1, customer.getCustomerCreditPoints());
				break;
			default:
				break;

			}
			preparedStatement.setString(2, customer.getCustomerID());
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				System.out.println("Update Failed");
			} else {
				System.out.println("Updated");
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Customer> displayAllCustomers() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Customer> customerList = new ArrayList();
		

		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from customer";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				LocalDate customerDOB= LocalDate.parse(resultSet.getString(4),formatter);
				Customer customer = new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),customerDOB,resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getLong(8));
				customerList.add(customer);
			}
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return customerList;
		
	}

	@Override
	public Customer getCustomer(String customer_id) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Customer customer=null;
		try {

			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from customer where customer_id=?");
			preparedStatement.setString(1, customer_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				LocalDate customerDOB= LocalDate.parse(resultSet.getString(4),formatter);
				customer = new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),customerDOB,resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getLong(8));

			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public void updateCustomerCredit(Long creditPoint,String customerID) {
		
		try {
			Connection connection = ds.getConnection();
			String sqlQuery= "update customer set customer_credit_points=customer_credit_points+? where customer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, creditPoint);
			preparedStatement.setString(2,customerID);
			preparedStatement.executeUpdate();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void reduceCustomerCredit(Long creditPoint, String customerID) {

		try {
			Connection connection = ds.getConnection();
			String sqlQuery= "update customer set customer_credit_points=customer_credit_points-? where customer_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, creditPoint);
			preparedStatement.setString(2,customerID);
			preparedStatement.executeUpdate();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
