package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.bean.Order;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.Impl.LineServiceImpl;
import com.briup.app.estore.service.Impl.OrderServiceImpl;

/**
 * Servlet implementation class DelOrderServlet
 */
@WebServlet("/orderRemove")
public class DelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	IOrderService orderService = new OrderServiceImpl();
	
	ILineService lineService = new LineServiceImpl();
    public DelOrderServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderId = request.getParameter("id");
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("isLogin");
		
		String msg ="删除成功";
		String location = "user/order.jsp";
		
		try {
			
			//先删除订单关联的清单
			lineService.deleteByOrderId(Integer.parseInt(orderId));
			
			//再删除订单
			orderService.deleteOrder(Integer.parseInt(orderId));
			
			
			//删除成功后，查询出来展示
			List<Order> orderInDb = orderService.selectByCustomerId(customer.getId());
			
			session.setAttribute("orderInDb", orderInDb);
			
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			msg="删除不成功"+e.getMessage();
			location="index.jsp";
			response.sendRedirect(location);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
