package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;

public class EmployeeScoreDAOImpl implements EmployeeScoreDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void addScore(String employeeID, Long score) {
		
		try
		{
			Connection connection =ds.getConnection();
			String sqlQuery = "update employee_score set score=score+? where employee_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, score);
			preparedStatement.setString(2, employeeID);
			preparedStatement.executeUpdate();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

}
