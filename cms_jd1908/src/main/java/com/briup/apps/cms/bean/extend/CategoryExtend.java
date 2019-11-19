package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Category;

public class CategoryExtend extends Category{
	
	private List<Article> Articles;
	
	private List<Category> Categorys;
	
	public List<Category> getCategorys() {
		return Categorys;
	}

	public void setCategorys(List<Category> categorys) {
		Categorys = categorys;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
