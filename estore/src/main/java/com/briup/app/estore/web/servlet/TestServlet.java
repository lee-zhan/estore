package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.mapper.BookMapper;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/Test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession openSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		BookMapper mapper = openSession.getMapper(BookMapper.class);
		
		List<Book> list = mapper.selectAll();
		
		list.forEach(System.out::println);
		
		PrintWriter writer = response.getWriter();
		
		writer.print("hello");
		
		Book book = new Book("test", 100D);
		
		System.out.println("before"+book.getId());
		mapper.insert(book);
		System.out.println("after"+book.getId());
		
		openSession.commit();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
