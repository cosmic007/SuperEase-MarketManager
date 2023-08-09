package com.shopeasemanager.service;

import java.time.LocalDateTime;
import java.util.List;

import com.shopeasemanager.dao.BillDAOImpl;
import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.BillDetails;

public class BillServiceImpl implements BillService {
	
	BillDAOImpl billDAOImpl = new BillDAOImpl();

	@Override
	public boolean createBill(Bill bill) {
		
		return billDAOImpl.createBill(bill);
	}

	@Override
	public Long getBillNoByDOP(LocalDateTime dateOfPurchase) {
		
		return billDAOImpl.getBillNoByDOP(dateOfPurchase);
	}

	@Override
	public boolean generateBill(List<BillDetails> billDetailsList) {
		
		return billDAOImpl.generateBill(billDetailsList);
	}

	@Override
	public List<BillDetails> getBillDetailsList(Long billNo) {

		return billDAOImpl.getBillDetailsList(billNo);
	}

	@Override
	public boolean completeBillGeneration(Bill bill) {
		
		return billDAOImpl.completeBillGeneration(bill);
	}

	@Override
	public String getCustomerFromBill(Long billNo) {
		// TODO Auto-generated method stub
		return billDAOImpl.getCustomerFromBill(billNo);
	}

	@Override
	public String getEmployeeFromBill(Long billNo) {
		// TODO Auto-generated method stub
		return billDAOImpl.getEmployeeFromBill(billNo);
	}

	@Override
	public Bill getBillFromBillNo(Long billNo) {
		
		return billDAOImpl.getBillFromBillNo(billNo);
	}


	

}
