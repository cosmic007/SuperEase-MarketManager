package com.shopeasemanager.service;

import java.time.LocalDateTime;
import java.util.List;

import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.BillDetails;

public interface BillService {

	public boolean createBill(Bill bill);

	public Long getBillNoByDOP(LocalDateTime dateOfPurchase);

	public boolean generateBill(List<BillDetails> billDetailsList);

	public List<BillDetails> getBillDetailsList(Long billNo);

	public boolean completeBillGeneration(Bill bill);
	
	
	
	public String getCustomerFromBill(Long billNo);
	
	public String getEmployeeFromBill(Long billNo);

	public Bill getBillFromBillNo(Long billNo);

}
