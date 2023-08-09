package com.shopeasemanager.dbconnectionpool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnectionPool {
	
	public static ComboPooledDataSource dataSource;
	
	static
	{
		try
		{
			dataSource= new ComboPooledDataSource();
			
			InputStream inputStream = new FileInputStream("resources/db.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			
			dataSource.setDriverClass(properties.getProperty("DRIVER"));
			dataSource.setJdbcUrl(properties.getProperty("CONNECTION_STRING"));
			dataSource.setUser(properties.getProperty("USERNAME"));
			dataSource.setPassword(properties.getProperty("PASSWORD"));
			
			dataSource.setInitialPoolSize(5);
			dataSource.setMinPoolSize(5);
			dataSource.setAcquireIncrement(5);
			dataSource.setMaxPoolSize(20);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static javax.sql.DataSource getDataSource()
	{
		return dataSource;
	}
	

}
