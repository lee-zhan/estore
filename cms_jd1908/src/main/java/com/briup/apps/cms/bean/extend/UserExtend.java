package com.briup.apps.cms.bean.extend;

import java.util.ArrayList;
import java.util.List;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.User;

public class UserExtend extends User {
    public static final String STATUS_NORMAL="正常";
    public static final String STATUS_FORBIDDEN="禁用";
//	一个用户有多种身份
	List<Role> roles = new ArrayList<>();

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}	
