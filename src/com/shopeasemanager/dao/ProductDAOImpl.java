package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Product;

public class ProductDAOImpl implements ProductDAO {

	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public void addProduct(Product product) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter productCategory code:");
			String productCategoryCode = scanner.nextLine();
			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into product values(?,?,?,?,?)");
			preparedStatement.setLong(1, product.getProductID());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getProductBrand());
			preparedStatement.setDouble(4, product.getProductRate());
			preparedStatement.setDouble(5, product.getProductGst());
			int status = preparedStatement.executeUpdate();
			if (status == 0) {
				System.out.println("Failed to Add Product");
			} else {
				System.out.println("Product Added Successfully");
				String initializeCountQuery="Insert into purchase_stats values(?,0)";
				preparedStatement=connection.prepareStatement(initializeCountQuery);
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.executeUpdate();
				String sql = "insert into pcmapping(product_id,category_code) values(?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.setString(2, productCategoryCode);
				preparedStatement.executeUpdate();
				String sql2 = "insert into  purchase_stats(product_id,purchase_count) values(?,?)";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.setLong(2, 0);
				preparedStatement.executeUpdate();
				String sql3 = "insert into discount(product_id,discount_amount) values(?,?)";
				preparedStatement = connection.prepareStatement(sql3);
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.setDouble(2, 0);
				preparedStatement.executeUpdate();
				String sql4 = "insert into stock(product_id,available_stock) values(?,?)";
				preparedStatement = connection.prepareStatement(sql4);
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.setLong(2, 0);
				preparedStatement.executeUpdate();
				
				
			}

			
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void updateProduct(Product product, String selectedAttribute) {

		try {

			Connection connection = ds.getConnection();
			String sqlQuery = "update product set " + selectedAttribute + " =? where product_id="
					+ product.getProductID();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			switch (selectedAttribute) {
			case "product_name":
				preparedStatement.setString(1, product.getProductName());
				break;
			case "product_brand":
				preparedStatement.setString(1, product.getProductBrand());
				break;
			case "product_rate":
				preparedStatement.setDouble(1, product.getProductRate());
				break;
			case "product_gst":
				preparedStatement.setDouble(1, product.getProductGst());
				break;
			default:
				break;

			}
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
	public List<Product> displayAllProducts() {
		List<Product> productList = new ArrayList<>();

		try {
			Connection connection = ds.getConnection();
			String sqlQuery = "select * from product";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				Product product = new Product(resultSet.getLong("product_id"), resultSet.getString("product_name"),
						resultSet.getString("product_brand"), resultSet.getDouble("product_rate"),
						resultSet.getDouble("product_gst"));
				productList.add(product);
			}
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public Product getProduct(Long productID) {
		Product product = null;
		try {

			Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from product where product_id=" + productID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDouble(4), resultSet.getDouble(5));

			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

}
