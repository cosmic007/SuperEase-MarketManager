package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Customer;

public interface CustomerService {
public void addCustomer(Customer customer);
	
	public void updateCustomer(Customer customer,String selectedCustomer);
	
	public List<Customer> displayAllCustomers();
	
	public Customer getCustomer(String customer_id);
	
	public void updateCustomerCredit(Long creditPoint,String customerID);
	
	
	public void reduceCustomerCredit(Long creditPoint,String customerID);

}
