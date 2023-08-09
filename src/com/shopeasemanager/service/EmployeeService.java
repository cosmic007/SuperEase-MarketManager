package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public void updateEmployee(Employee employee, String selectedEmployee);

	public List<Employee> displayAllEmployees();

	public Employee getEmployee(String employeeID);

}
