package com.shopeasemanager.entity;

public class Salary {
	private Employee employee;
	private Double salaryAmount;
	private Double bonus;
	public Salary(Employee employee, Double salaryAmount, Double bonus) {
		super();
		this.employee = employee;
		this.salaryAmount = salaryAmount;
		this.bonus = bonus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Double getSalaryAmount() {
		return salaryAmount;
	}
	public void setSalaryAmount(Double salaryAmount) {
		this.salaryAmount = salaryAmount;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	
	
	

}
