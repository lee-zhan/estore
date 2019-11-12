package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.shoppingcart.ShoppingCart;

/**
 * Servlet implementation class EditOrderlineServlet
 */
@WebServlet("/updateProduct.action")
public class EditOrderlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditOrderlineServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String productid = request.getParameter("productid");
		String number = request.getParameter("number");
		
		System.out.println(productid);
		System.out.println(number);
		
		ShoppingCart shoppingCar = (ShoppingCart) session.getAttribute("shoppingcar");
	
		shoppingCar.update(Integer.parseInt(productid), Integer.parseInt(number));
		
//		Map<Integer, Orderline> map = shoppingCar.getLines();
//		
//		Orderline orderline = map.get(Integer.parseInt(productid));
		//System.out.println(orderline);
		
		//修改后重新计算总价
		session.setAttribute("cost", shoppingCar.getCost());
		
		//修改后重新回到购物清单
		String location = "user/shoppingCart.jsp";
		response.sendRedirect(location);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
