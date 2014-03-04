package com.zhuwei.web.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.Order;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class OrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		BusinessService service = new BusinessServiceImpl();
		Order order = service.findOrder(order_id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
