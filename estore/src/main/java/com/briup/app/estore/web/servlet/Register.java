package com.briup.app.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.Impl.CustomerServiceImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private ICustomerService customerService = new CustomerServiceImpl();
	
	
    public Register() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "register.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收请求携带的参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		//2.把参数封装成对象
		Customer customer = new Customer(name,password,address,zip,telephone,email);
		
		//3.传给service层方法经行逻辑处理
		String page = "/login.jsp";
		String msg = "注册成功，请登录";
		try {
			customerService.register(customer);
		} catch (Exception e) {
			e.printStackTrace();
			page="/register.jsp";
			msg = "注册失败"+e.getMessage();
		} 
		
		//4.根据service处理的结果，选择响应的页面
		request.setAttribute("msg", msg);
		
		request.getRequestDispatcher(page).forward(request, response);
		
		
	}

}
