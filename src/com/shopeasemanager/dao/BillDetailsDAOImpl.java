package com.shopeasemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.shopeasemanager.dbconnectionpool.DBConnectionPool;
import com.shopeasemanager.entity.Bill;

public class BillDetailsDAOImpl implements BillDetailsDAO {
	
	DataSource ds = DBConnectionPool.getDataSource();

	

}
