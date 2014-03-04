package com.zhuwei.web.manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhuwei.domain.Book;
import com.zhuwei.domain.Category;
import com.zhuwei.domain.Page;
import com.zhuwei.service.BusinessService;
import com.zhuwei.service.impl.BusinessServiceImpl;
import com.zhuwei.utils.WebUtils;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("addbookUI")){
			addbookUI(request,response);
		}else if(method.equals("add")){
			addbook(request, response);
		}else if (method.equals("listbook")) {
			listbook(request,response);
		}
	}

	private void listbook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		BusinessService service = new BusinessServiceImpl();
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
	}

	private void addbook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = doupload(request);
			BusinessService service = new BusinessServiceImpl();
			book.setId(WebUtils.makeID());
			service.addBook(book);
			request.setAttribute("message", "Addbook Success!");
		} catch (Exception e) {	
			e.printStackTrace();
			request.setAttribute("message", "Addbook Fail!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}
	
	private Book doupload(HttpServletRequest request){
		//save the image file in images and others in Book.class
		Book book = new Book();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(book, name, value);
				}else {
					String fileName = item.getName();
					String saveFilename = makeFileName(fileName);//the name of image save in the disk
					String savePath = this.getServletContext().getRealPath("/images");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);
					
					int len = 0;
					byte[] buffer = new byte[1024];
					while((len = in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					in.close();
					out.close();
					item.delete();
					book.setImage(saveFilename);
				}
			}
			return book;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String makeFileName(String filename){
		//upload image do not need original name
		String txt = filename.substring(filename.lastIndexOf("."));
		System.out.println(txt);
		return UUID.randomUUID().toString() + txt;
	}

	private void addbookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BusinessService service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
