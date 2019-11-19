package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface ICategoryService {
//多表查询
	
	//级联查询，显示栏目下所有的文章
	CategoryExtend findById(long id);
	
	//级联查询，显示栏目底下的子栏目
	CategoryExtend findByIdWithSon(long id);
	
	//级联删除，删除栏目的时候栏目对应的文章也删除
	void deleByIdWithArticle(long id);
	
//单表查询
	
    List<Category> findAll();

    void saveOrUpdate(Category category) throws CustomerException;
    
    void deleById(long id) throws CustomerException;
    
    void batchDelete(long[] ids) throws CustomerException;
}
