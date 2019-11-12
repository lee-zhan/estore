package com.briup.app.estore.service.Impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.mapper.BookMapper;
import com.briup.app.estore.service.IBookService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class BookServiceImpl implements IBookService {
	SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	BookMapper mapper = sqlSession.getMapper(BookMapper.class);
	
	
	@Override
	public List<Book> findAllBook() throws Exception {
		List<Book> books = mapper.selectAll();
		
		if(books == null) {
			throw new Exception("书库中没有书籍");
		}
		return books;
	}
	
}
