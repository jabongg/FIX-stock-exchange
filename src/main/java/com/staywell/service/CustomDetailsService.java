package com.staywell.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.staywell.repository.CustomerDao;
import com.staywell.repository.HotelDao;

@Service
public class CustomDetailsService  {

	@Autowired
	private CustomerDao cDao;


	@Autowired
	private HotelDao hDao;

}
