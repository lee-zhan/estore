package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("栏目相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@ApiOperation("查询所有的栏目")
	@GetMapping("findAll")
	public Message findAll() {
		List<Category> findAll = categoryService.findAll();
		return MessageUtil.success(findAll);
	}
	
	@ApiOperation("根据id查询栏目并且级联显示文章")
	@GetMapping("selecById")
	public Message selecById(long id) {
		CategoryExtend categoryExtend = categoryService.findById(id);
		return MessageUtil.success(categoryExtend);
	}
	
	@ApiOperation("根据id查询栏目并且级联显示子栏目")
	@GetMapping("findByIdWithSon")
	public Message findByIdWithSon(long id) {
		CategoryExtend categoryExtend = categoryService.findByIdWithSon(id);
		return MessageUtil.success(categoryExtend);
	}
	
	
	@ApiOperation("更新或新增栏目")
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(Category category) {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("更新成功");
	}
	
	@ApiOperation("根据id删除栏目")
	@GetMapping("deleteById")
	public Message deleteById(@ApiParam(value = "栏目编号",required = true) @RequestParam(value = "id",required = false) long id) {
		categoryService.deleById(id);
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation("根据id删除栏目并且级联删除栏目下的文章")
	@GetMapping("deleByIdWithArticle")
	public Message deleByIdWithArticle(@ApiParam(value = "栏目编号",required = true) @RequestParam(value = "id",required = false) long id) {
		categoryService.deleByIdWithArticle(id);
		return MessageUtil.success("删除成功");
	}
	

	//	前后端用json格式数据交互，各自可以把json转化为想要的对象，
	@ApiOperation("批量删除栏目")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "ids",value = "一组栏目编号",required = true,paramType = "body")
			)
	@PostMapping(value="batchDelete",produces = "application/json;charset=UTF-8")
	public Message batchDelete(@RequestBody long[] ids) {
		categoryService.batchDelete(ids);
		return MessageUtil.success("删除成功");
	}
	
}
