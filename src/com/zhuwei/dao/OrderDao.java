package com.zhuwei.dao;

import java.util.List;

import com.zhuwei.domain.Order;
import com.zhuwei.domain.User;

public interface OrderDao {

	void add(Order order);

	Order find(String id);
	
	List<Order> find(User user);

	List<Order> getAll(boolean state);

	void update(Order order);

}