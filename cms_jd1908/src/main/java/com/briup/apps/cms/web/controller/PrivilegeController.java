package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.PrivilegeExtend;
import com.briup.apps.cms.service.IBasePrivilegeService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.PrivilegeTree;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("权限相关接口")
@RequestMapping("/privilege")
@RestController
public class PrivilegeController {
	
	@Autowired
	IBasePrivilegeService basePrivilegeService;
	
	@ApiOperation("查询所有的权限")
	@GetMapping("findAll")
	public Message findAll(){
		List<Privilege> findAll = basePrivilegeService.findAll();
		return MessageUtil.success(findAll);
	}
	
	@ApiOperation("通过一级权限id查二级权限")
	@GetMapping("findByParentId")
	public Message findByParentId(Long parentId){
		List<Privilege> findByParentId = basePrivilegeService.findByParentId(parentId);
		return MessageUtil.success(findByParentId);
	}
	
	@ApiOperation("保存或者更新")
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(Privilege privilege){
		basePrivilegeService.saveOrUpdate(privilege);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation("查找权限树，就是查询一级权限级联查询出二级权限")
	@GetMapping("findPrivilegeTree")
	public Message findPrivilegeTree(){
		List<PrivilegeExtend> findPrivilegeTree = basePrivilegeService.findPrivilegeTree();
		return MessageUtil.success(findPrivilegeTree);
	}
	
	
	@ApiOperation("通过id查询该用户有什么权限")
	@GetMapping("findByUserId")
	public Message findByUserId(long id){
		List<PrivilegeExtend> findByUserId = basePrivilegeService.findByUserId(id);
		return MessageUtil.success(findByUserId);
	}
	
	
	
	
	
	
	
	
	
}
