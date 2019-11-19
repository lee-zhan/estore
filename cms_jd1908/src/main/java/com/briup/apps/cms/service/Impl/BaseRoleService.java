package com.briup.apps.cms.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.bean.RolePrivilegeExample;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.UserExample;
import com.briup.apps.cms.bean.UserRoleExample;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.RolePrivilegeMapper;
import com.briup.apps.cms.dao.UserRoleMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.service.IBaseRoleService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class BaseRoleService implements IBaseRoleService {
	
	@Resource
	RoleMapper roleMapper;
	
	@Resource
	RoleExtendMapper roleExtendMapper;

	@Resource
	UserRoleMapper userRoleMapper;
	
	@Resource
	RolePrivilegeMapper rolePrivilegeMapper;
	
	@Override
	public List<Role> findAll() {
		RoleExample example = new RoleExample();
		List<Role> roles = roleMapper.selectByExample(example);
		return roles;
	}

	@Override
	public List<RoleExtend> cascadePrivilegeFindAll() {
		List<RoleExtend> list = roleExtendMapper.selectAll();
		return list;
	}

	@Override
	public void saveOrUpdate(Role baseRole) throws CustomerException {
		 if(baseRole.getId()!=null){
			 roleMapper.updateByPrimaryKey(baseRole);
	        } else {
	            // 判断角色名是否被占用
	        	RoleExample roleExample = new RoleExample();
	        	roleExample.createCriteria().andNameEqualTo(baseRole.getName());
	            List<Role> roles = roleMapper.selectByExample(roleExample);
	            if(roles.size()>0){
	                throw new CustomerException("该用户已经被占用");
	            }
	            roleMapper.insert(baseRole);
	        }
	}

	@Override
	public void deleteById(long id) throws CustomerException {
//		1.判断删除的用户存不存在
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIdEqualTo(id);
		List<Role> roles = roleMapper.selectByExample(roleExample);
		if(roles.size()<=0) {
			throw new CustomerException("删除的角色不存在");
		}
//		2.先删除桥表中的外键关系列，用户-角色，角色-权限表中的依赖
		//删除用户角色桥表中的依赖
		UserRoleExample userRoleExample = new UserRoleExample();
		userRoleExample.createCriteria().andRoleIdEqualTo(id);
		userRoleMapper.deleteByExample(userRoleExample);
		//角色权限桥表中的依赖
		RolePrivilegeExample example = new RolePrivilegeExample();
		example.createCriteria().andRoleIdEqualTo(id);
		rolePrivilegeMapper.deleteByExample(example);
//		3.最后删除该id的角色
		roleMapper.deleteByPrimaryKey(id);
	}

//	授权
	@Override
	public void authorization(long roleId, List<Long> privilegeIds) {
		
		
	}

}
