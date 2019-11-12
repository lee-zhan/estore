package com.briup.app.estore.service.Impl;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.mapper.CustomerMapper;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.util.MD5Util;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class CustomerServiceImpl implements ICustomerService {
	SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
	
	@Override
	public void register(Customer customer) throws Exception {

		
		
		Customer customerInDb = mapper.selectByName(customer.getName());
		
		if(customerInDb !=null) {
			throw new Exception("用户名不可用或者已经重复了："+customer.getName());
		}
		
		mapper.insert(customer);
		sqlSession.commit();
		
	}

	@Override
	public Customer login(String name,String password) throws Exception {
		
		Customer customerInDb = mapper.selectByName(name);
		
		if(customerInDb == null) {
			throw new Exception("用户名不存在："+name+"请重新登录或者注册");
		}else if(!customerInDb.getPassword().equals(password)) {
			throw new Exception("密码错误请，重新登录");
		}
		sqlSession.commit();
		
		return customerInDb;
	}

	//提交订单时修改用户信息，就更新数据库信息
	@Override
	public void confirmOrder(Customer customer) throws Exception {
		try {
			mapper.updateByPrimaryKey(customer);
			sqlSession.commit();
		} catch (Exception e) {
			throw new Exception("修改失败");
		}
		
		
	}
}
