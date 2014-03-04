package com.zhuwei.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.User;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;
import com.zhuwei.utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			
			User user = new User();
			user.setId(WebUtils.makeID());
			user.setUsername(username);
			user.setPassword(password);
			user.setPhone(phone);
			user.setAddress(address);
			user.setEmail(email);
			
			BusinessService service = new BusinessServiceImpl();
			service.registerUser(user);
			request.setAttribute("message", "Register Seccess!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Register Fail!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
