package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Employee;
import com.shopeasemanager.entity.Salary;

public class SalaryDAOImpl implements SalaryDAO {
	DataSource ds = DBConnectionPool.getDataSource();
	
	
	
	
	
	
	
	
	

	@Override
	public List<Salary> displaySalaryDetails() {
		ArrayList<Salary> salaryList = new ArrayList<>();
		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from salary";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(resultSet.getString(1));
				Salary salary = new Salary(employee, resultSet.getDouble(2), resultSet.getDouble(3));
				salaryList.add(salary);
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return salaryList;
	}

	@Override
	public void updateSalary(String employeeID, double newSalaryAmount) {

		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "update salary set salary_amount=? where employee_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setDouble(1, newSalaryAmount);
			preparedStatement.setString(2, employeeID);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@Override
	public void updateBonus(String employeeID, double newBonus) {
		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "update salary set bonus=? where employee_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setDouble(1, newBonus);
			preparedStatement.setString(2, employeeID);
			preparedStatement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void addNewSalary(Salary salary) {
		try
		{
			Connection connection = ds.getConnection();
			String sqlQuery = "insert into salary values(?,?,?)";
			PreparedStatement preparedStatement =connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, salary.getEmployee().getEmployeeID());
			preparedStatement.setDouble(2, salary.getSalaryAmount());
			preparedStatement.setDouble(3, salary.getBonus());
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
