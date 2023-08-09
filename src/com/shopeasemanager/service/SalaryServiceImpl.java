package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.SalaryDAOImpl;
import com.shopeasemanager.entity.Salary;

public class SalaryServiceImpl implements SalaryService{

	SalaryDAOImpl salaryDAOImpl = new SalaryDAOImpl();
	
	@Override
	public List<Salary> displaySalaryDetails() {
		
		return salaryDAOImpl.displaySalaryDetails();
	}

	@Override
	public void updateSalary(String employeeID, double newSalaryAmount) {
		
		salaryDAOImpl.updateSalary(employeeID, newSalaryAmount);
		
	}

	@Override
	public void updateBonus(String employeeID, double newBonus) {
		salaryDAOImpl.updateBonus(employeeID, newBonus);
		
	}

	@Override
	public void addNewSalary(Salary salary) {
		salaryDAOImpl.addNewSalary(salary);
		
	}

}
