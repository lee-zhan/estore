package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.extend.PrivilegeExtend;

public interface PrivilegeExtendMapper {
	
	Privilege selectByRoleId();
	
//	查询一级权限级联查询出二级权限
	List<PrivilegeExtend> selectAll();
	
//	根据用户id查询拥有的权限
	List<PrivilegeExtend> selectByUserId(long id);
} 