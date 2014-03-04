package com.zhuwei.web.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class ConfirmOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String order_id = request.getParameter("order_id");
			BusinessService service = new BusinessServiceImpl();
			service.confirmOrder(order_id);
			request.setAttribute("message", "Order Cheked, Please send!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","Confirm Fail!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
