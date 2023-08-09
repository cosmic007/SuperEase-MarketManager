package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void addEmployee(Employee employee) {
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employee values(?,?,?,?,?,?)");
			preparedStatement.setString(1, employee.getEmployeeID());
			preparedStatement.setString(2, employee.getEmployeeFirstName());
			preparedStatement.setString(3, employee.getEmployeeLastName());
			Date dob= Date.valueOf(employee.getEmployeeDOB());
			preparedStatement.setDate(4, dob);
			preparedStatement.setLong(5, employee.getEmployeePhNo());
			preparedStatement.setString(6, employee.getEmployeeAddress());
			
			
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				System.out.println("Failed to Add Employee");
			} else {
				System.out.println("Employee Added Successfully");
				String sqlQuery="insert into employee_score values (?,?)";
				preparedStatement=connection.prepareStatement(sqlQuery);
				preparedStatement.setString(1,employee.getEmployeeID());
				preparedStatement.setLong(2, 0);
				preparedStatement.executeUpdate();
				
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Employee employee, String selectedEmployee) {
		try {

			Connection connection = ds.getConnection();
			String sqlQuery = "update employee set " + selectedEmployee + " =? where employee_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			switch (selectedEmployee) {
			case "employee_firstname":
				preparedStatement.setString(1, employee.getEmployeeFirstName());
				break;
			case "employee_lastname":
				preparedStatement.setString(1, employee.getEmployeeLastName());
				break;
			case "employee_dob":
				String dob = String.valueOf(employee.getEmployeeDOB());       
				preparedStatement.setString(1, dob);
				break;
			case "employee_phno":
				preparedStatement.setLong(1, employee.getEmployeePhNo());
				
				break;
			case "employee_address":
				preparedStatement.setString(1, employee.getEmployeeAddress());
				break;
			default:
				break;

			}
			preparedStatement.setString(2, employee.getEmployeeID());
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				System.out.println("Update Failed");
			} else {
				System.out.println("Updated");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public ArrayList<Employee> displayAllEmployees() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ArrayList<Employee> employeeList = new ArrayList();

		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from employee";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				LocalDate employeeDOB= LocalDate.parse(resultSet.getString(4),formatter);
				Employee employee= new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),employeeDOB,resultSet.getLong(5),resultSet.getString(6));
				employeeList.add(employee);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return employeeList;
	}

	@Override
	public Employee getEmployee(String employeeID) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Employee employee=null;
		try {

			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from employee where employee_id=?" );
			preparedStatement.setString(1, employeeID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				LocalDate employeeDOB= LocalDate.parse(resultSet.getString(4),formatter);
				employee= new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),employeeDOB,resultSet.getLong(5),resultSet.getString(6));
				
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return employee;
	}

}
