package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.service.IBookService;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.Impl.BookServiceImpl;
import com.briup.app.estore.service.Impl.CustomerServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private ICustomerService customerService = new CustomerServiceImpl();
	
	private IBookService bookService = new BookServiceImpl();
    
	public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/login.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		String page = "/index.jsp";
		String msg = "登录成功";
		String bookmsg="书籍展示成功";
		try {
			//调用service方法登录，登录失败此处会抛异常
			Customer customer = customerService.login(name,password);
			session.setAttribute("isLogin", customer);
			
			try {
				//如果登录成功，需要查询所有书籍到主页面展示
				List<Book> allBook = bookService.findAllBook();
				application.setAttribute("allbook", allBook);
			} catch (Exception e) {
				bookmsg="书籍查询失败"+e.getMessage();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			page="/login.jsp";
			msg = "登录失败"+e.getMessage();
		}
		session.setAttribute("msg", msg);
		session.setAttribute("name", name);
		session.setAttribute("bookmsg", bookmsg);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
