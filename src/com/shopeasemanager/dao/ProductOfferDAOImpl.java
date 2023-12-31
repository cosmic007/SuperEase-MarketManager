package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Offer;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.ProductOffer;
import com.shopeasemanager.service.OfferServiceImpl;
import com.shopeasemanager.service.ProductServiceImpl;

public class ProductOfferDAOImpl implements ProductOfferDAO {

	DataSource ds = DBConnectionPool.getDataSource();

	@Override
	public Boolean addProductOffer(ProductOffer productOffer) {
		Boolean success = false;

		try {
			Connection connection = ds.getConnection();

			String sqlQuery = "insert into product_offer_mapping(product_id,offer_id,start_date,end_date) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, productOffer.getProduct().getProductID());
			preparedStatement.setLong(2, productOffer.getOffer().getOfferID());
			Date startDate=Date.valueOf(productOffer.getStartDate());
			Date endDate=Date.valueOf(productOffer.getEndDate());
			preparedStatement.setDate(3, startDate);
			preparedStatement.setDate(4, endDate);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				success = true;
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return success;
	}

	@Override
	public Boolean updateProductOffer(ProductOffer productOffer) {
		Boolean success = false;

		try {
			Connection connection = ds.getConnection();

			String sqlQuery = "update product_offer_mapping set offer_id=?,start_date=?,end_date=? where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, productOffer.getOffer().getOfferID());
			
			Date startDate=Date.valueOf(productOffer.getStartDate());
			Date endDate=Date.valueOf(productOffer.getEndDate());
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);
			preparedStatement.setLong(4, productOffer.getProduct().getProductID());

			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				success = true;
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return success;
	}

	@Override
	public List<ProductOffer> displayProductOffer() {
		OfferServiceImpl offerServiceImpl = new OfferServiceImpl();
		List<ProductOffer> productOfferList = new ArrayList<>();

		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		try {

			Connection connection = ds.getConnection();

			String sqlQuery = "select * from product_offer_mapping";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = productServiceImpl.getProduct(resultSet.getLong(2));
				Offer offer = new Offer(resultSet.getLong(3),offerServiceImpl.getOffer(resultSet.getLong(3)));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDate=LocalDate.parse(resultSet.getString(4),formatter);
				LocalDate endDate=LocalDate.parse(resultSet.getString(5),formatter);
				ProductOffer productOffer = new ProductOffer(resultSet.getLong(1), product, offer,startDate,endDate,resultSet.getString(6));
				productOfferList.add(productOffer);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return productOfferList;
	}

	@Override
	public ProductOffer getProductOffer(Long productId) {
		OfferServiceImpl offerServiceImpl = new OfferServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();

		ProductOffer productOffer = null;

		try {

			Connection connection = ds.getConnection();

			String sqlQuery = "select * from product_offer_mapping where product_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setLong(1, productId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Product product = productServiceImpl.getProduct(resultSet.getLong(2));
				Offer offer = new Offer(resultSet.getLong(3), offerServiceImpl.getOffer(resultSet.getLong(3)));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDate=LocalDate.parse(resultSet.getString(4),formatter);
				LocalDate endDate=LocalDate.parse(resultSet.getString(5),formatter);
				productOffer = new ProductOffer(resultSet.getLong(1), product, offer,startDate,endDate,resultSet.getString(6));
			}

			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return productOffer;
	}

}
