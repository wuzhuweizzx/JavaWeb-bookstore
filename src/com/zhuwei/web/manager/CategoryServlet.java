package com.zhuwei.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuwei.domain.Category;
import com.zhuwei.domain.User;
import com.zhuwei.exception.PrivilegeException;
import com.zhuwei.service.BusinessService;
import com.zhuwei.utils.ServiceFactory;
import com.zhuwei.utils.WebUtils;


//deal with category CRUD
public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if(method.equals("add")){
			add(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("find")){
			find(request,response);
		}else if(method.equals("listall")){
			listall(request,response);
		}else{
			request.setAttribute("message", "Invalid Operation!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	private void listall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BusinessService service = ServiceFactory.getServiceFactory().CreatedService("com.zhuwei.service.impl.BusinessServiceImpl", BusinessService.class, (User)request.getSession().getAttribute("user"));
			List<Category> categories = service.getAllCategories();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/manager/listcategories.jsp").forward(request, response);
		}  catch (Exception e) {	
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message", e.getCause().getMessage());
				request.getRequestDispatcher("/message.jsp").forward(request, response);		
			}else{
				e.printStackTrace();
				request.setAttribute("message", "List Category Fail");
				request.getRequestDispatcher("/message.jsp").forward(request, response);		
			}
		}
	}

	private void find(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			Category category = new Category();
			category.setName(name);
			category.setDescription(description);
			category.setId(WebUtils.makeID());
			
			BusinessService service = ServiceFactory.getServiceFactory().CreatedService("com.zhuwei.service.impl.BusinessServiceImpl", BusinessService.class, (User)request.getSession().getAttribute("user"));
			service.addCategory(category);
			request.setAttribute("message", "Add Category Success!!!");
		} catch (Exception e) {	
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message", e.getCause().getMessage());
			}else{
				e.printStackTrace();
				request.setAttribute("message", "Add Category Fail!!!");
			}
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
