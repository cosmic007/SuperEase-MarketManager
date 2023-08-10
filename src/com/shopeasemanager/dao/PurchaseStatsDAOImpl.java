package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.LeastSellingProduct;

public class PurchaseStatsDAOImpl implements PurchaseStatsDAO {

	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void updatePurchaseCount(Long product_id, Long quantity) {
		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "update purchase_stats set purchase_count=? where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, quantity);
			preparedStatement.setLong(2, product_id);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void incrementPurchaseCount(Long product_id) {
		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "update purchase_stats set purchase_count=purchase_count+1 where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, product_id);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<LeastSellingProduct> displayLeastSellingByCateory(String categoryCode, int limit) {
		List<LeastSellingProduct> leastSellingProductList = new ArrayList<>();

		try {
	
			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = null;
			if (!categoryCode.equalsIgnoreCase("ALL")) {

				String sqlQuery = "select purchase_stats.product_id,product_name,product_rate,category_name,purchase_count from purchase_stats join product on purchase_stats.product_id=product.product_id join pcmapping on product.product_id=pcmapping.product_id join productcategory on pcmapping.category_code=productcategory.category_code where productcategory.category_code=? order by purchase_count limit ?"
						+ "";
				preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setString(1, categoryCode);
				preparedStatement.setLong(2, limit);
			} else if (categoryCode .equalsIgnoreCase("ALL")) {
				String sqlQuery = "select purchase_stats.product_id,product_name,product_rate,category_name,purchase_count from purchase_stats join product on purchase_stats.product_id=product.product_id join pcmapping on product.product_id=pcmapping.product_id join productcategory on pcmapping.category_code=productcategory.category_code order by purchase_count limit ?";
				preparedStatement = connection.prepareStatement(sqlQuery);
				preparedStatement.setLong(1, limit);

			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LeastSellingProduct leastSellingProduct = new LeastSellingProduct(resultSet.getLong(1),
						resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getInt(5));
				leastSellingProductList.add(leastSellingProduct);
			}

			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return leastSellingProductList;
	}

}
