package com.briup.apps.cms.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.UserExample;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

@Service
public class BaseUserServiceImpl implements IBaseUserService {
	
	@Resource
	UserMapper userMapper;
	
	@Resource
	UserExtendMapper userExtendMapper;
	
//	用户登录验证
	@Override
	public User login(UserVM userVM) throws CustomerException {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo(userVM.getUsername());
		List<User> users = userMapper.selectByExample(userExample);
		if(users.size()<=0) {
			throw new CustomerException("用户不存在");
		}
		User user = users.get(0);
		if(!userVM.getPassword().equals(user.getPassword())) {
			throw new CustomerException("密码错误");
		}
		return user;
	}
	
//	通过id查询用户并且级联查询该用户的身份
	@Override
	public UserExtend findById(long id) {
		UserExtend userExtend = userExtendMapper.selectById(id);
		return userExtend;
	}

	@Override
	public List<User> findAll() {
		UserExample example = new UserExample();
		List<User> users = userMapper.selectByExample(example);
		return users;
	}

	@Override
	public List<UserExtend> cascadeRoleFindAll() {
		List<UserExtend> selectAll = userExtendMapper.selectAll();
		return selectAll;
	}

	@Override
	public void saveOrUpdate(User baseUser) throws CustomerException {
		 if(baseUser.getId()!=null){
			 userMapper.updateByPrimaryKey(baseUser);
	        } else {
	            // 判断用户名是否被占用
	            UserExample example = new UserExample();
	            example.createCriteria().andUsernameEqualTo(baseUser.getUsername());
	            List<User> list = userMapper.selectByExample(example);
	            if(list.size()>0){
	                throw new CustomerException("该用户已经被占用");
	            }
	            // 初始化
	            baseUser.setRegisterTime(new Date().getTime());
	            baseUser.setStatus(UserExtend.STATUS_NORMAL);
	            userMapper.insert(baseUser);
	        }
	}

	@Override
	public void changeStatus(long id, String status) throws CustomerException {
		UserExtend userExtend = this.findById(id);
		if(userExtend == null) {
			throw new CustomerException("用户不存在");
		}
		userExtend.setStatus(status);
		userMapper.updateByPrimaryKey(userExtend);
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		UserExtend userExtend = this.findById(id);
		if(userExtend == null) {
			throw new CustomerException("用户不存在");
		}
		userMapper.deleteByPrimaryKey(id);
	}

//	设置权限
	
	@Override
	public void setRoles(long id, List<Long> roles) {
		
	}


}
