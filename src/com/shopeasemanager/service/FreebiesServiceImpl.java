package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.dao.FreebiesDAOImpl;
import com.shopeasemanager.entity.Freebies;

public class FreebiesServiceImpl implements FreebiesService {
	
	FreebiesDAOImpl freebiesDAOImpl = new FreebiesDAOImpl();

	@Override
	public void sellFreeProducts(Freebies freebies) {
		
		freebiesDAOImpl.sellFreeProducts(freebies);
		
	}

	@Override
	public List<Freebies> displayFreebies(Long billno) {
		
		return freebiesDAOImpl.displayFreebies(billno);
	}

}
