package com.zhuwei.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.Order;
import com.zhuwei.domain.User;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class ListUserOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			request.setAttribute("message", "Login First!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		BusinessService service = new BusinessServiceImpl();
		List<Order> orders = service.findUserOrder(user);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/client/listuserorder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
