package com.briup.apps.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.JwtTokenUtil;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
	
   @Autowired
    private IBaseUserService baseUserService;

   /**
    * 用户登录验证基本功能
    * */
    @ApiOperation(value = "登录")
    @PostMapping(value = "login")
    public Message login(@RequestBody UserVM userVM){
        // 1. 认证用户的用户名和密码
        User user = baseUserService.login(userVM);
        // 2. 如果登录成功产生token,将token缓存起来，返回
        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        // 3. 如果登录失败
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return MessageUtil.success(map);
    }


    @ApiOperation(value = "退出")
    @PostMapping(value = "logout")
    public Message logout(){
        // 1. 将缓存中token移除
        // 2. 其他
        return MessageUtil.success("success");
    }

    @ApiOperation(value = "通过token获取用户信息")
    @GetMapping(value = "info")
    public Message info(String token){
        // 1. 通过token获取用户信息  {id,use,gender,roles:[]}
//        long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
        UserExtend baseUserExtend = baseUserService.findById(3);
        return MessageUtil.success(baseUserExtend);
    }
   /**
    * 用户的增删改查
    * 
    * */ 
    
    @ApiOperation(value = "保存或更新")
    @PostMapping(value = "saveOrUpdate")
    public Message saveOrUpdate(User baseUser){
        baseUserService.saveOrUpdate(baseUser);
        return MessageUtil.success("更新成功");
    }
    
    @ApiOperation("查询所有的用户")
    @GetMapping(value="findAll")
    public Message findAll() {
    	List<User> users = baseUserService.findAll();
    	return MessageUtil.success(users);
    }
    
    @ApiOperation("根据id查询用户及用户的角色")
    @GetMapping(value="findById")
    public Message findById(long id) {
    	UserExtend userExtend = baseUserService.findById(id);
    	return MessageUtil.success(userExtend);
    }
    
    @ApiOperation("级联查询所有用户")
    @GetMapping(value="cascadeRoleFindAll")
    public Message cascadeRoleFindAll() {
    	List<UserExtend> cascadeRoleFindAll = baseUserService.cascadeRoleFindAll();
    	return MessageUtil.success(cascadeRoleFindAll);
    }
    
    @ApiOperation("根据id删除用户")
    @GetMapping(value="deleteById")
    public Message deleteById(long id) {
    	baseUserService.deleteById(id);
    	return MessageUtil.success("删除成功");
    }
    
    @ApiOperation("根据id修改用户状态")
    @GetMapping(value="changeStatus")
    public Message changeStatus(long id,String status) {
    	baseUserService.changeStatus(id, status);
    	return MessageUtil.success("状态修改成功");
    }
}
