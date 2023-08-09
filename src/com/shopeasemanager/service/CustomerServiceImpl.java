package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.CustomerDAOImpl;
import com.shopeasemanager.entity.Customer;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();

	@Override
	public void addCustomer(Customer customer) {
		
		customerDAOImpl.addCustomer(customer);
		
	}

	@Override
	public void updateCustomer(Customer customer, String selectedCustomer) {
		customerDAOImpl.updateCustomer(customer, selectedCustomer);
		
	}

	@Override
	public List<Customer> displayAllCustomers() {
		
		return customerDAOImpl.displayAllCustomers();
	}

	@Override
	public Customer getCustomer(String customer_id) {
		
		return customerDAOImpl.getCustomer(customer_id);
	}

	@Override
	public void updateCustomerCredit(Long creditPoint, String customerID) {
		customerDAOImpl.updateCustomerCredit(creditPoint, customerID);
		
	}

	@Override
	public void reduceCustomerCredit(Long creditPoint, String customerID) {
		customerDAOImpl.reduceCustomerCredit(creditPoint, customerID);
		
	}

}
