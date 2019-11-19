package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

/**
 * @program: cms_jd1911
 * @description: 用户接口
 * @author: charles
 * @create: 2019-11-15 15:17
 **/

public interface IBaseUserService {
    /**
     * @Description: 用户登录
     * @Param: [UserVM]
     * @return: com.briup.apps.cms.bean.extend.UserExtend
     * @Author: charles
     * @Date: 2019-11-16
     */
	User login(UserVM userVM) throws CustomerException;
    /**
     * @Description: 通过id查询
     * @Param: [id]
     * @return: com.briup.apps.cms.bean.extend.BaseUserExtend
     * @Author: charles
     * @Date: 2019-11-16
     */
    UserExtend findById(long id);

    /**
     * @Description: 查询所有
     * @Param: []
     * @return: java.util.List<com.briup.apps.cms.bean.BaseUser>
     * @Author: charles
     * @Date: 2019-11-16
     */
    List<User> findAll();
    
    /** 
     * @Description: 级联查询所有
     * @Param: [] 
     * @return: java.util.List<com.briup.apps.cms.bean.extend.BaseUserExtend> 
     * @Author: charles 
     * @Date: 2019-11-17 
     */ 
    List<UserExtend> cascadeRoleFindAll();

    /**
     * @Description:  保存或更新
     * @Param: [baseUser]
     * @return: void
     * @Author: charles
     * @Date: 2019-11-16
     */
    void saveOrUpdate(User baseUser) throws CustomerException;

    /** 
     * @Description: 更新状态
     * @Param: [status] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-16 
     */ 
    void changeStatus(long id,String status) throws CustomerException;
    
    /** 
     * @Description: 通过id删除
     * @Param: [id] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-17
     */ 
    void deleteById(long id) throws CustomerException;
    
    /** 
     * @Description: 设置角色
     * @Param: [id, roles] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-17 
     */ 
    void setRoles(long id, List<Long> roles);
}
