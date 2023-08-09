package com.shopeasemanager.entity;

import java.time.LocalDate;

public class Customer {
	private String customerID;
	private String customerFirstName;
	private String customerLastName;
	private LocalDate customerDOB;
	private String customerEmail;
	private Long customerPhNo;
	private String customerAddress;
	private Long customerCreditPoints;
	public Customer(String customerID, String customerFirstName, String customerLastName, LocalDate customerDOB,
			String customerEmail, Long customerPhNo, String customerAddress, Long customerCreditPoints) {
		super();
		this.customerID = customerID;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerDOB = customerDOB;
		this.customerEmail = customerEmail;
		this.customerPhNo = customerPhNo;
		this.customerAddress = customerAddress;
		this.customerCreditPoints = customerCreditPoints;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public LocalDate getCustomerDOB() {
		return customerDOB;
	}
	public void setCustomerDOB(LocalDate customerDOB) {
		this.customerDOB = customerDOB;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Long getCustomerPhNo() {
		return customerPhNo;
	}
	public void setCustomerPhNo(Long customerPhNo) {
		this.customerPhNo = customerPhNo;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Long getCustomerCreditPoints() {
		return customerCreditPoints;
	}
	public void setCustomerCreditPoints(Long customerCreditPoints) {
		this.customerCreditPoints = customerCreditPoints;
	}
	
	

}
