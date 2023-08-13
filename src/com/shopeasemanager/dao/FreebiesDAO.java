package com.shopeasemanager.dao;

import java.util.List;

import com.shopeasemanager.entity.Freebies;

public interface FreebiesDAO {
	
	
	public void sellFreeProducts(Freebies freebies);
	
	public List<Freebies> displayFreebies(Long billno);

}
