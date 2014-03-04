package com.zhuwei.service;

import java.util.List;

import com.zhuwei.domain.Book;
import com.zhuwei.domain.Cart;
import com.zhuwei.domain.Category;
import com.zhuwei.domain.Database;
import com.zhuwei.domain.Order;
import com.zhuwei.domain.Page;
import com.zhuwei.domain.Privilege;
import com.zhuwei.domain.User;
import com.zhuwei.utils.Permission;

public interface BusinessService {

	/**Category Methods**/
	@Permission("AddCategory")
	void addCategory(Category category);

	@Permission("SearchCategory")
	Category findCategory(String id);
	
	@Permission("ListCategory")
	List<Category> getAllCategories();

	/**Book Methods**/
	void addBook(Book book);

	Book findBook(String id);

	Page getBookPageData(String pagenum);

	//Get Page Data under category 
	Page getBookPageData(String pagenum, String category_id);

	void buybook(Cart cart, Book book);

	/*User Method*/
	void registerUser(User user);

	User findUser(String id);

	User login(String username, String password);
	
	public List<Privilege> getUserPrivileges(String userid);

	/*Order Method*/
	void createOrder(Cart cart, User user);

	List<Order> listOrder(String state);

	Order findOrder(String order_id);

	void confirmOrder(String order_id);

	List<Order> findUserOrder(User user);
	
	/*Database Method*/
	void backupDatabase(String path);

	List<Database> getAllDatabase();

	void restoreDatabase(String id);

	void deleteDatabase(String id);

}