package com.zhuwei.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.zhuwei.domain.Database;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class DatabaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("backup")){
			backup(request,response);
		}
		if(method.equals("list")){
			list(request,response);
		}
		if(method.equals("restore")){
			restore(request,response);
		}
		if(method.equals("delete")){
			delete(request,response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String id = request.getParameter("id");
			BusinessService service = new BusinessServiceImpl();
			service.deleteDatabase(id);
			request.setAttribute("message", "Delete Database Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Delete Database Failure!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);	
	}

	private void restore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			BusinessService service = new BusinessServiceImpl();
			service.restoreDatabase(id);
			request.setAttribute("message", "Restore Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Restore Failure!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BusinessService service = new BusinessServiceImpl();
			List<Database> databases = service.getAllDatabase();
			request.setAttribute("databases", databases);
			request.getRequestDispatcher("/manager/listdatabases.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "List Databases Failure!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}

	private void backup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String path = this.getServletContext().getRealPath("/backup");
			BusinessService service = new BusinessServiceImpl();
			service.backupDatabase(path);
			request.setAttribute("message", "Backup Success!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Backup Failure!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
