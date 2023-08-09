package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Salary;

public interface SalaryService {

	public List<Salary> displaySalaryDetails();

	public void updateSalary(String employeeID, double newSalaryAmount);

	public void updateBonus(String employeeID, double newBonus);

	public void addNewSalary(Salary salary);

}
