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
 * Servlet implementation class removeProductServlet
 */
@WebServlet("/removeProduct.action")
public class removeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public removeProductServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ShoppingCart shoppcar = (ShoppingCart) session.getAttribute("shoppingcar");
		
		String bookId = request.getParameter("productid");
		System.out.println(bookId);
		shoppcar.delete(Integer.parseInt(bookId));
		
		//Map<Integer, Orderline> map = shoppcar.getLines();

		//map.remove(Integer.parseInt(bookId));
		//map.forEach((k,v)->System.out.println(k +""+ v));
//		
//		session.setAttribute("map", map);
		
		//删除后重新计算总价
		session.setAttribute("cost", shoppcar.getCost());
		//删除后重新回到购物清单
		String location = "user/shoppingCart.jsp";
		response.sendRedirect(location);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
