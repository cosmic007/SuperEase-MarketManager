package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.PCMappingDAOImpl;
import com.shopeasemanager.entity.PCMapping;

public class PCMappingServiceImpl implements PCMappingService{
	
	PCMappingDAOImpl pcMappingDAOImpl = new PCMappingDAOImpl();

	@Override
	public List<PCMapping> displayAllItems() {
		
		return pcMappingDAOImpl.displayAllItems();
	}

	

	

}
