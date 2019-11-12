package com.briup.app.estore.service;

import java.util.List;

import com.briup.app.estore.bean.Order;

public interface IOrderService {
	void saveOrder(Order orders) throws Exception;
	
	List<Order> selectAll() throws Exception;
	
	void deleteOrder(Integer orderId) throws Exception;
	
	List<Order> selectByCustomerId(Integer customerId)throws Exception;
}
