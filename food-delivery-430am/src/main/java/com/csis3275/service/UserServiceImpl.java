package com.csis3275.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.dao.CustomerDAOImpl;
import com.csis3275.model.Customer;
import com.csis3275.model.Login;

public class UserServiceImpl {

	@Autowired
	public CustomerDAOImpl customerDAOImpl;
	
	
	
	public int register(Customer customer) {
		return 0;
		//return customerDAOImpl.createCustomer(customer);
	}

	
	public Customer validateUser(Login login) {
		return customerDAOImpl.validateCustomer(login);
	}

}
