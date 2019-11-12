package com.briup.app.estore.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.mapper.OrderMapper;
import com.briup.app.estore.mapper.OrderlineMapper;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class LineServiceImpl implements ILineService {
	SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	OrderlineMapper mapper = (OrderlineMapper) sqlSession.getMapper(OrderlineMapper.class);
	
	@Override
	public void saveOrderLine(List<Orderline> orderlines) throws Exception {
		mapper.insert(orderlines);
		sqlSession.commit();
	}


	@Override
	public void deleteByOrderId(Integer orderId) {
		mapper.deleteByOrderId(orderId);
		sqlSession.commit();
		
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}
}
