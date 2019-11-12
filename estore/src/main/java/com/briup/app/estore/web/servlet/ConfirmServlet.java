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
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.Impl.LineServiceImpl;
import com.briup.app.estore.service.Impl.OrderServiceImpl;

/**
 * 提交顶点后把信息存到数据库
 * 顾客的对应的订单，订单包含的清单要级联的插入数据库
 */
@WebServlet("/comfirmOrder")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	IOrderService orderService = new OrderServiceImpl();
	
	ILineService lineService = new LineServiceImpl();
	
    public ConfirmServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Order orders = (Order) session.getAttribute("order");
		
		Customer cutormer = orders.getCutormer();
		
		List<Orderline> orderlines = orders.getOrderlines();
		
		String msg ="订单保存成功";
		
		try {
			//保存顾客对应的订单，订单对应的清单项
			orderService.saveOrder(orders);
			
			for (Orderline orderline : orderlines) {
				System.out.println(orderline);
				orderline.setOrder(orders);
			}
			lineService.saveOrderLine(orderlines);
			
			
			//保存成功后，查询出来展示
			List<Order> orderInDb = orderService.selectByCustomerId(cutormer.getId());
			
//			for (Order od : orderInDb) {
//				od.setCutormer(cutormer);
//				od.setOrderlines(orderlines);
//				System.out.println("订单id"+od.getId());
//			}
			
			
			session.setAttribute("orderInDb", orderInDb);
			
			//保存成功，提交成功跳到主页面
			String location = "user/order.jsp";
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			msg="订单保存失败"+e.getMessage();
			//保存失败，在原页面
			String location = "user/confirmOrder.jsp";
			response.sendRedirect(location);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
