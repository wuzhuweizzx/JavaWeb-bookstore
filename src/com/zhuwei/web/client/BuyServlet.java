package com.zhuwei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.Book;
import com.zhuwei.domain.Cart;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String book_id = request.getParameter("book_id");
			BusinessService service = new BusinessServiceImpl();
			Book book = service.findBook(book_id);
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			if(cart == null){
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			
			service.buybook(cart, book);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Failure Buy!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
