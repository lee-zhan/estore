package com.briup.app.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.shoppingcart.ShoppingCart;

/**
 * Servlet implementation class ClearCartServlet
 */
@WebServlet("/shopcartClear")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClearCartServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ShoppingCart shoppingCar = (ShoppingCart) session.getAttribute("shoppingcar");
		
		shoppingCar.clear();
		
		//清空后重新计算总价
		session.setAttribute("cost", shoppingCar.getCost());
		
		//清空后重新回到购物清单
		String location = "user/shoppingCart.jsp";
		response.sendRedirect(location);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
