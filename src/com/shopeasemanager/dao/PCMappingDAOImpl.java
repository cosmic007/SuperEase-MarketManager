package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.PCMapping;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.ProductCategory;

public class PCMappingDAOImpl implements PCMappingDAO {
	DataSource ds= DBConnectionPool.getDataSource();

	@Override
	public List<PCMapping> displayAllItems() {
		List<PCMapping>  pcMappingList = new ArrayList<>();
		try {
			Connection connection = ds.getConnection();
			String sqlQuery="select * from pcmapping";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Product product =new Product();
				product.setProductID(resultSet.getLong("product_id"));
				ProductCategory productCategory = new ProductCategory();
				productCategory.setCategoryID(resultSet.getString("category_code"));
				PCMapping pcMapping = new PCMapping(resultSet.getLong("pcmapping_id"),product,productCategory);
				pcMappingList.add(pcMapping);
			}
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return pcMappingList;
	}


}
