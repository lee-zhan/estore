package com.briup.app.estore.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Order;
import com.briup.app.estore.mapper.OrderMapper;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class OrderServiceImpl implements IOrderService{
	SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	OrderMapper mapper = (OrderMapper) sqlSession.getMapper(OrderMapper.class);
	
	@Override
	public void saveOrder(Order orders) throws Exception {
		if(orders!=null) {
			mapper.insert(orders);
			sqlSession.commit();
		}else{
			throw new Exception("插入order为空");
		}
		
	}

	@Override
	public List<Order> selectAll() throws Exception {
		List<Order> orders = mapper.selectAll();
		if(orders == null) {
			throw new Exception("查询订单为空");
		}
		return orders;
	}

	@Override
	public void deleteOrder(Integer orderId) throws Exception {
		mapper.deleteByPrimaryKey(orderId);
		sqlSession.commit();
	}

	@Override
	public List<Order> selectByCustomerId(Integer customerId) throws Exception {
		List<Order> order = mapper.selectByCustomerId(customerId);
		if(order == null) {
			throw new Exception("查询订单为空");
		}
		sqlSession.commit();
		return order;
	}

}
