package com.shopeasemanager.entity;

import java.time.LocalDateTime;

public class Bill {
	
	private Long billNo;
	private LocalDateTime dateOfPurchase;
	private Customer customer;
	private Employee employee;
	private Double discountApplied;
	private Long creditEarned;
	private Long creditUsed;
	private Double totalRate;
	public Bill(Long billNo, LocalDateTime dateOfPurchase, Customer customer, Employee employee, Double discountApplied,
			Long creditEarned, Long creditUsed, Double totalRate) {
		super();
		this.billNo = billNo;
		this.dateOfPurchase = dateOfPurchase;
		this.customer = customer;
		this.employee = employee;
		this.discountApplied = discountApplied;
		this.creditEarned = creditEarned;
		this.creditUsed = creditUsed;
		this.totalRate = totalRate;
	}
	
	
	public Bill(Long billNo) {
		super();
		this.billNo = billNo;
	}


	public Bill(LocalDateTime dateOfPurchase, Customer customer, Employee employee) {
		super();
		this.dateOfPurchase = dateOfPurchase;
		this.customer = customer;
		this.employee = employee;
	}

	public Bill() {
		// TODO Auto-generated constructor stub
	}
	public Long getBillNo() {
		return billNo;
	}
	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}
	public LocalDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Double getDiscountApplied() {
		return discountApplied;
	}
	public void setDiscountApplied(Double discountApplied) {
		this.discountApplied = discountApplied;
	}
	public Long getCreditEarned() {
		return creditEarned;
	}
	public void setCreditEarned(Long creditEarned) {
		this.creditEarned = creditEarned;
	}
	public Long getCreditUsed() {
		return creditUsed;
	}
	public void setCreditUsed(Long creditUsed) {
		this.creditUsed = creditUsed;
	}
	public Double getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(Double totalRate) {
		this.totalRate = totalRate;
	}
	
	
	
	

}
