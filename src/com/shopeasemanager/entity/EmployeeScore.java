package com.shopeasemanager.entity;

public class EmployeeScore {
	
	private Employee employee;
	private Long score;
	public EmployeeScore(Employee employee, Long score) {
		super();
		this.employee = employee;
		this.score = score;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	
	
	

}
