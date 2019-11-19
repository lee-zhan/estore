package com.briup.apps.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.dao.extend.CategoryExtendMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Resource
	private CategoryMapper cateroryMapper;
	
    @Resource
    private ArticleMapper articleMapper;
	
	@Resource
	private CategoryExtendMapper categoryExtendMapper;
	
	@Override
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
		return cateroryMapper.selectByExample(example );
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		//栏目的id存在，则表示更新的操作
		if(category.getId() !=null) {
			cateroryMapper.updateByPrimaryKey(category);
		}
		//栏目的id不存在表示为新插入信息，而且栏目的名字不能重复
		else {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = cateroryMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("栏目的名字不能重复");
			}
			cateroryMapper.insert(category);
		}
		
	}

//	根据id删除
	@Override
	public void deleById(long id) throws CustomerException {
		Category category = cateroryMapper.selectByPrimaryKey(id);
		if(category == null) {
			throw new CustomerException("要删除的栏目不存在");
		}
		cateroryMapper.deleteByPrimaryKey(id);
	}
	
//	批量删除
	@Override
	public void batchDelete(long[] ids) throws CustomerException {
		for (long id : ids) {
			//先删除文章
			ArticleExample example = new ArticleExample();
			example.createCriteria().andCategoryIdEqualTo(id);
			articleMapper.deleteByExample(example);
		}
		//再删除栏目
		categoryExtendMapper.batchDelete(ids);
	}

//	查询该栏目下的所有文章
	@Override
	public CategoryExtend findById(long id) {
		Category category = cateroryMapper.selectByPrimaryKey(id);
		
		if(category==null) {
			throw new CustomerException("要查找的栏目不存在");
		}
			
		CategoryExtend categoryExtend = categoryExtendMapper.selectById(id);
		
		return categoryExtend;
	}
//	查询该栏目下的子栏目
	@Override
	public CategoryExtend findByIdWithSon(long id) {
		Category category = cateroryMapper.selectByPrimaryKey(id);
		
		if(category==null) {
			throw new CustomerException("要查找的栏目不存在");
		}
		CategoryExtend categoryExtend = categoryExtendMapper.findByIdWithSon(id);		
		return categoryExtend;
	}
//删除栏目的时候级联删除文章
	@Override
	public void deleByIdWithArticle(long id) {
		Category category = cateroryMapper.selectByPrimaryKey(id);
		
		if(category==null) {
			throw new CustomerException("要删除的栏目不存在");
		}
		//先删除文章
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		//在删除对应的栏目
		cateroryMapper.deleteByPrimaryKey(id);
	}	
}
