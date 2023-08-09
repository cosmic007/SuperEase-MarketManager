package com.shopeasemanager.dao;

import java.util.ArrayList;

import com.shopeasemanager.entity.Employee;

public interface EmployeeDAO {
	public void addEmployee(Employee employee);

	public void updateCustomer(Employee employee, String selectedEmployee);

	public ArrayList<Employee> displayAllEmployees();

	public Employee getEmployee(String employeeID);

}
