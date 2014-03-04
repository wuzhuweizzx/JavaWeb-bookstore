package com.zhuwei.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.zhuwei.dao.BookDao;
import com.zhuwei.dao.CategoryDao;
import com.zhuwei.dao.DatabaseDao;
import com.zhuwei.dao.OrderDao;
import com.zhuwei.dao.UserDao;
import com.zhuwei.domain.Book;
import com.zhuwei.domain.Cart;
import com.zhuwei.domain.CartItem;
import com.zhuwei.domain.Category;
import com.zhuwei.domain.Database;
import com.zhuwei.domain.Order;
import com.zhuwei.domain.OrderItem;
import com.zhuwei.domain.Page;
import com.zhuwei.domain.Privilege;
import com.zhuwei.domain.User;
import com.zhuwei.service.BusinessService;
import com.zhuwei.utils.Daofactory;
import com.zhuwei.utils.JdbcUtils;
import com.zhuwei.utils.Permission;
import com.zhuwei.utils.WebUtils;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cdao = Daofactory.getInstance().createDao("com.zhuwei.dao.impl.CategoryDaoImpl", CategoryDao.class);
	private BookDao bdao = Daofactory.getInstance().createDao("com.zhuwei.dao.impl.BookDaoImpl", BookDao.class);
	private UserDao udao = Daofactory.getInstance().createDao("com.zhuwei.dao.impl.UserDaoImpl", UserDao.class);
	private OrderDao odao = Daofactory.getInstance().createDao("com.zhuwei.dao.impl.OrderDaoImpl", OrderDao.class);
	private DatabaseDao ddao = Daofactory.getInstance().createDao("com.zhuwei.dao.impl.DatabaseDaoImpl", DatabaseDao.class);
	
	@Permission("AddCategory")
	public void addCategory(Category category){
		cdao.add(category);
	}
	
	
	@Permission("SearchCategory")
	public Category findCategory(String id ){
		return cdao.find(id);
	}
	
	@Permission("ListCategory")
	public List<Category> getAllCategories() {
		return cdao.getAll();
	}
	
	
	public void addBook(Book book){
		bdao.add(book);
	}
	
	
	public Book findBook(String id){
		return bdao.find(id);
	}
	
	//List all book we need pages
	public Page getBookPageData(String pagenum){
		int totalrecord = bdao.getTotalRecord();
		Page page = null;
		if(pagenum == null ){
			page = new Page(1, totalrecord);
		}else {
			page = new Page(Integer.parseInt(pagenum), totalrecord);
		}
		
		List<Book> list = bdao.getPageDate(page.getStartindex(), page.getPagesize());
		page.setList(list);
		return page;
	}
	
	//Get Page Data under category 
	public Page getBookPageData(String pagenum, String category_id) {
		int totalrecord = bdao.getTotalRecord(category_id);
		
		Page page = null;
		if(pagenum == null ){
			page = new Page(1, totalrecord);
		}else {
			page = new Page(Integer.parseInt(pagenum), totalrecord);
		}
		List<Book> list = bdao.getPageDate(page.getStartindex(), page.getPagesize(), category_id);
		page.setList(list);
		return page;
	}
	
	

	
	public void buybook(Cart cart, Book book) {
		cart.add(book);
		
	}
	
	/*User Method*/
	
	public void registerUser(User user) {
		udao.add(user);	
	}
	

	public User findUser(String id){
		return udao.find(id);
	}
	
	
	public User login(String username, String password){
		return udao.find(username, password);
	}
	
	public List<Privilege> getUserPrivileges(String userid){
		return udao.getPrivileges(userid);
	}
	
	/*Order Method*/
	
	public void createOrder(Cart cart, User user){
		if(cart == null){
			throw new RuntimeException("Sorry, No good in Cart");
		}
		Order order = new Order();
		order.setId(WebUtils.makeID());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		
		Map<String, CartItem> map = cart.getMap();
		for(Map.Entry<String, CartItem> me : map.entrySet()){
			CartItem citem = me.getValue();
			OrderItem oItem = new OrderItem();
			oItem.setBook(citem.getBook());
			oItem.setId(WebUtils.makeID());
			oItem.setPrice(citem.getPrice());
			oItem.setQuantity(citem.getQuantity());
			order.getOrderitems().add(oItem);
		}
		
		odao.add(order);
		
	}

	
	public List<Order> listOrder(String state) {
		return odao.getAll(Boolean.parseBoolean(state));		
	}

	
	public Order findOrder(String order_id) {
		return odao.find(order_id);
	}


	public void confirmOrder(String order_id) {
		Order order = odao.find(order_id);
		order.setState(true);
		odao.update(order);		
	}

	
	public List<Order> findUserOrder(User user) {
		return odao.find(user);
		
	}
	
	/*Database method*/
	
	public void backupDatabase(String path){
		try {
			int num = ddao.getTotalRecord();
			String filename = "bookstore" + (num++) + ".sql";//Get the name of backupdatabase name
			String fullpath = path + "\\" + filename;
			String command = "cmd /c mysqldump -uroot -p1234 bookstoreonline>" + fullpath;
			Runtime.getRuntime().exec(command);//exe the command in cmd
			
			Database db = new Database();
			db.setId(WebUtils.makeID());
			db.setName(filename);
			db.setBackuptime(new Date());
			db.setPath(path);
			ddao.add(db);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public List<Database> getAllDatabase() {
		return ddao.getAll();
	}
	
	
	public void restoreDatabase(String id) {
		// TODO Auto-generated method stub
		try {
			Database db = ddao.find(id);
			String filename = db.getName();
			String path = db.getPath();
			String command = "cmd /c mysql -uroot -p1234 bookstoreonline<" + path + "\\" + filename;
			
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteDatabase(String id) {
		
		try {
			JdbcUtils.startTransaction();
			Database db = ddao.find(id);
			ddao.delete(id);
			
			String filename = db.getPath() + "\\" + db.getName();
			File file = new File(filename);
			if(file.exists()){
				file.delete();
			}
			JdbcUtils.commit();
		} catch (Exception e) {
			JdbcUtils.rollback();
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.close();
		}
		
		
	}
	
}