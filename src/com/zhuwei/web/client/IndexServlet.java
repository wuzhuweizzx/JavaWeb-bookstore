package com.zhuwei.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.Category;
import com.zhuwei.domain.Page;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("getAll")){
			getAll(request, response);
		}else if (method.equals("listBookWithCategory")) {
			listBookWithCategory(request,response);
		}
		
	}
	
	

	private void listBookWithCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String category_id = request.getParameter("category_id");
		String pagenum = request.getParameter("pagenum");
		BusinessService service = new BusinessServiceImpl();
		Page page = service.getBookPageData(pagenum, category_id);
		request.setAttribute("categories", service.getAllCategories());
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}



	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessService service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategories();		
		String pagenum = request.getParameter("pagenum");
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("categories", categories);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
