package com.shopeasemanager.service;

import java.util.List;

import com.shopeasemanager.entity.Freebies;

public interface FreebiesService {
	public void sellFreeProducts(Freebies freebies);
	public List<Freebies> displayFreebies(Long billno);

}
