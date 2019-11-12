package com.briup.app.estore.service;

import com.briup.app.estore.bean.Customer;

public interface ICustomerService {
	void register(Customer customer) throws Exception;
	
	Customer login(String name,String password) throws Exception;
	
	void confirmOrder(Customer customer)throws Exception;
	
}
