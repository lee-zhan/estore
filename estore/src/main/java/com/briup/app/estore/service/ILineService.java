package com.briup.app.estore.service;

import java.util.List;

import com.briup.app.estore.bean.Orderline;

public interface ILineService {
	int deleteByPrimaryKey(Integer id);
	
	void saveOrderLine(List<Orderline> orderlines) throws Exception;
	
	void deleteByOrderId(Integer orderId);
}
