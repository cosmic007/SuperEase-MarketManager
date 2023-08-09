package com.shopeasemanager.entity;

import java.time.LocalDate;

public class Employee {
	
	private String employeeID;
	private String employeeFirstName;
	private String employeeLastName;
	private LocalDate employeeDOB;
	private Long employeePhNo;
	private String employeeAddress;
	
	
	
	
	
	public Employee(String employeeID, String employeeFirstName, String employeeLastName, LocalDate employeeDOB,
			Long employeePhNo, String employeeAddress) {
		super();
		this.employeeID = employeeID;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeDOB = employeeDOB;
		this.employeePhNo = employeePhNo;
		this.employeeAddress = employeeAddress;
	}
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String employeeID) {
		super();
		this.employeeID = employeeID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public LocalDate getEmployeeDOB() {
		return employeeDOB;
	}
	public void setEmployeeDOB(LocalDate employeeDOB) {
		this.employeeDOB = employeeDOB;
	}
	public Long getEmployeePhNo() {
		return employeePhNo;
	}
	public void setEmployeePhNo(Long employeePhNo) {
		this.employeePhNo = employeePhNo;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	
	
	
	
	

}
