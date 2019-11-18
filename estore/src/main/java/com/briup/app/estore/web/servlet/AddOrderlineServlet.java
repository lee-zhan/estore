package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.bean.Order;
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.shoppingcart.ShoppingCart;
/*
 * 添加购物车
 * */
@WebServlet("/addorder")
public class AddOrderlineServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static ShoppingCart sc;
	
	static List<Orderline> list;
	//订单list
	static List<Order> orderlist;
	
	static {
		sc = new ShoppingCart(); 
		//清单list
		list = new ArrayList<>();
		
		orderlist = new ArrayList<>();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
//		System.out.println("id:"+id);
//		System.out.println("name:"+name);
//		System.out.println("price:"+price);
		
		
		//书本对象
		Book book = new Book(Integer.parseInt(id),name,Double.parseDouble(price));
		
		//本次登录用户
		Customer customer = (Customer) session.getAttribute("isLogin");
		
		//清单
		Orderline orderline = new Orderline();
		
		//订单清单id根据主键生成
		//orderline.setId(Integer.parseInt(id));
		
		orderline.setBook(book);
		
		sc.add(orderline);
		
		double cost = sc.getCost();
		
		list.add(orderline);
		
		//订单
		Order orders = new Order(cost, new Date(), customer,list );
		
		orderlist.add(orders);
		
		//封装订单
		
		//把订单封装到顾客里
		customer.setOrder(orderlist);
		
		Map<Integer, Orderline> map = sc.getLines();
		
//		map.forEach((k,v)->System.out.println(k +""+ v));
		
		session.setAttribute("map", map);
		
		session.setAttribute("cost", cost);
		
		session.setAttribute("order", orders);
		
		session.setAttribute("customer", customer);
		
		session.setAttribute("shoppingcar", sc);
		
		String location = "user/shoppingCart.jsp";
		resp.sendRedirect(location);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
