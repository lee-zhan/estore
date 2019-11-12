package com.briup.app.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.Impl.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdate")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ICustomerService customerService = new CustomerServiceImpl();
	
    public CustomerUpdateServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "userinfo.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("isLogin");
		
		String password = request.getParameter("password2");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		if(password!=null)
			customer.setPassword(password);
		if(password!=null)
			customer.setAddress(address);
		if(password!=null)
			customer.setZip(zip);
		if(password!=null)
			customer.setTelephone(telephone);
		if(password!=null)
			customer.setEmail(email);
		
		String msg="修改成功";
		String location = "index.jsp";
		try {
			System.out.println(customer);
			
			customerService.confirmOrder(customer);
			
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			msg="修改失败"+e.getMessage();
			location = "user/userinfo.jsp";
			response.sendRedirect(location);
		}
	}

}
