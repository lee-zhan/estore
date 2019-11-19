package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;

public class PrivilegeExtend extends Privilege{
   private List<Privilege> sons;

	public List<Privilege> getSons() {
		return sons;
	}
	
	public void setSons(List<Privilege> sons) {
		this.sons = sons;
	}
   
   
}