package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.service.IBaseRoleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("角色相关接口")
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	IBaseRoleService baseRoleService;
	
	@ApiOperation("查询所有的角色")
	@GetMapping("findAll")
	public Message findAll(){
		List<Role> roles = baseRoleService.findAll();
		return MessageUtil.success(roles);
	}
	
	@ApiOperation("级联查询所有的角色和权限")
	@GetMapping("cascadePrivilegeFindAll")
	public Message cascadePrivilegeFindAll(){
		List<RoleExtend> privilegeFindAll = baseRoleService.cascadePrivilegeFindAll();
		return MessageUtil.success(privilegeFindAll);
	}
	
	@ApiOperation("保存或者更新")
	@GetMapping("saveOrUpdate")
	public Message saveOrUpdate(Role baseRole){
		baseRoleService.saveOrUpdate(baseRole);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation("根据id删除角色")
	@GetMapping("deleteById")
	public Message deleteById(long id){
		baseRoleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
}
