package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Order;
import com.briup.app.estore.bean.Orderline;

/**
 *  接收一个订单的id，根据订单的id查清单的全部信息，用来展示这个订单的信息
 */
@WebServlet("/ShowLineByOrderId")
public class ShowLineByOrderIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowLineByOrderIdServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String orderId = request.getParameter("id");
		
		System.out.println("订单明细"+orderId);
		
		@SuppressWarnings("unchecked")
		List<Order> orderInDb = (List<Order>) session.getAttribute("orderInDb");
		
		boolean flag = false;
		Order order = null;
		for (int i = 0; i < orderInDb.size(); i++) {
			order = orderInDb.get(i);
			
			if(order.getId() == Integer.parseInt(orderId)) {
				flag=true;
			}
			if(flag) 
				break;
		}
		
		
		List<Orderline> orderlines = order.getOrderlines();
		
		for (Orderline orderline : orderlines) {
			System.out.println(order.getId()+"清单:"+orderline);
		}
		
		
		session.setAttribute("showOrderInfo", orderlines);
		
		System.out.println("循环结束");
		String location = "user/orderinfo.jsp";
		response.sendRedirect(location);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
