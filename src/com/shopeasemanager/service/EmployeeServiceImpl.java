package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.EmployeeDAOImpl;
import com.shopeasemanager.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

	@Override
	public void addEmployee(Employee employee) {
		employeeDAOImpl.addEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee, String selectedEmployee) {
		employeeDAOImpl.updateCustomer(employee, selectedEmployee);
		
	}

	@Override
	public List<Employee> displayAllEmployees() {
		
		return employeeDAOImpl.displayAllEmployees();
	}

	@Override
	public Employee getEmployee(String employeeID) {
		
		return employeeDAOImpl.getEmployee(employeeID);
	}

}
