package com.zhuwei.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zhuwei.dao.OrderDao;
import com.zhuwei.domain.Book;
import com.zhuwei.domain.Order;
import com.zhuwei.domain.OrderItem;
import com.zhuwei.domain.User;
import com.zhuwei.exception.DaoException;
import com.zhuwei.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.OrderDao#add(com.zhuwei.domain.Order)
	 */
	public void add(Order order){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			//1. save order into order table
			String sql = "insert into orders(id, ordertime, price, state, user_id) values(?,?,?,?,?)";
			Object[] params = new Object[]{order.getId(), order.getOrdertime(), order.getPrice(), order.isState(), order.getUser().getId()};
			runner.update(sql,params);
			//2. Get all orderitems in OrderItem Table
			Set<OrderItem> set = order.getOrderitems();
			for(OrderItem item : set){
				sql = "insert into orderitem(id, quantity, price, book_id, orders_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(), item.getBook().getId(), order.getId()};
				runner.update(sql, params);
			}
			
			
		}catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.OrderDao#find(java.lang.String)
	 */
	public Order find(String id){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			//1. Get Order basic information
			String sql = "select * from orders where id=?";
			Object[] params = new Object[]{id};
			Order order = runner.query(sql, new BeanHandler(Order.class), params);
			
			//2. Find all orderitems in order
			sql = "select * from orderitem where orders_id=?";
			List<OrderItem> list = runner.query(sql, new BeanListHandler(OrderItem.class), order.getId());
			
			for(OrderItem item : list){
				sql = "select book.* from orderitem, book where orderitem.id=? and orderitem.book_id=book.id";
				params = new Object[]{item.getId()};
				Book book = runner.query(sql, new BeanHandler(Book.class),params);
				item.setBook(book);		
			}
			order.getOrderitems().addAll(list);
			//3. User who belongs to this order
			
			sql = "select user.* from orders, user where orders.id=? and orders.user_id= user.id";
			User user = runner.query(sql, new BeanHandler(User.class), id);
			order.setUser(user);
			return order;
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.OrderDao#getAll(boolean)
	 */
	public List<Order> getAll(boolean state){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = runner.query(sql, new BeanListHandler(Order.class), state);
			for(Order order : list){
				//current order belong to which user
				sql = "select user.* from orders, user where orders.id=? and orders.user_id= user.id";
				User user = runner.query(sql, new BeanHandler(User.class), order.getId());
				order.setUser(user);
			}
			return list;	
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhuwei.dao.impl.OrderDao#update(com.zhuwei.domain.Order)
	 */
	public void update(Order order){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//update order state
			String sql = "update orders set state=? where id=? ";
			Object[] params = new Object[]{order.isState(), order.getId()};
			runner.update(sql,params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public List<Order> find(User user){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select orders.* from orders where orders.user_id=?";
			List<Order> list = (List) runner.query(sql, new BeanListHandler(Order.class), user.getId());
			for(Order order : list ){
				sql = "select user.* from orders,user where orders.id=? and user.id=orders.user_id";
				User oUser = runner.query(sql, new BeanHandler(User.class), order.getId());
				order.setUser(oUser);
				
				sql = "select orderitem.* from orders,orderitem where orders.id=? and orderitem.orders_id=orders.id";
				List<OrderItem> orderitems = runner.query(sql, new BeanListHandler(OrderItem.class), order.getId());
				
				for(OrderItem item : orderitems){
					sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";
					Book book = runner.query(sql, new BeanHandler(Book.class), item.getId());
					item.setBook(book);
				}
				
				order.getOrderitems().addAll(orderitems);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
