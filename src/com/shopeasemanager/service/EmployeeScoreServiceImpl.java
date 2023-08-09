package com.shopeasemanager.service;

import com.shopeasemanager.dao.EmployeeScoreDAOImpl;

public class EmployeeScoreServiceImpl implements EmployeeScoreService {
	
	EmployeeScoreDAOImpl employeeScoreDAOImpl = new EmployeeScoreDAOImpl();

	@Override
	public void addScore(String employeeID, Long score) {
		
		employeeScoreDAOImpl.addScore(employeeID, score);
		
		
	}

}
