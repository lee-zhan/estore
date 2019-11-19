package com.briup.apps.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.PrivilegeExample;
import com.briup.apps.cms.bean.extend.PrivilegeExtend;
import com.briup.apps.cms.dao.PrivilegeMapper;
import com.briup.apps.cms.dao.extend.PrivilegeExtendMapper;
import com.briup.apps.cms.service.IBasePrivilegeService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

@Service
public class BasePrivilegeServiceImpl implements IBasePrivilegeService{
	
	@Resource
	PrivilegeMapper privilegeMapper;
	
	@Resource
	PrivilegeExtendMapper privilegeExtendMapper;
	
//	查询所有权限
	@Override
	public List<Privilege> findAll() {
		return privilegeMapper.selectByExample(new PrivilegeExample());
	}

//	通过一级权限id查二级权限
	@Override
	public List<Privilege> findByParentId(Long parentId) {
       PrivilegeExample example = new PrivilegeExample();
        if(parentId == null){
            example.createCriteria().andParentIdIsNull();
        } else {
            example.createCriteria().andParentIdEqualTo(parentId);
        }
        return privilegeMapper.selectByExample(example);
	}

//	增加权限
	@Override
	public void saveOrUpdate(Privilege privilege) throws CustomerException {
        if(privilege.getId()!=null){
        	privilegeMapper.updateByPrimaryKey(privilege);
        } else {
        	privilegeMapper.insert(privilege);
        }
	}

//	查找权限树，就是查询一级权限级联查询出二级权限
	@Override
	public List<PrivilegeExtend> findPrivilegeTree() {
		return privilegeExtendMapper.selectAll();
	}

//	通过id查询该用户有什么权限
	@Override
	public List<PrivilegeExtend> findByUserId(long id) {
		List<PrivilegeExtend> selectByUserId = privilegeExtendMapper.selectByUserId(id);
		if(selectByUserId.size()<=0) {
			throw new CustomerException("该用户没有权限");
		}
		return selectByUserId;
	}

}
