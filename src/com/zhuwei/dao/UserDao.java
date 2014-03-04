package com.zhuwei.dao;

import java.util.List;

import com.zhuwei.domain.Privilege;
import com.zhuwei.domain.User;

public interface UserDao {

	void add(User user);

	User find(String id);

	User find(String username, String password);
	
	public List<Privilege> getPrivileges(String id);

}