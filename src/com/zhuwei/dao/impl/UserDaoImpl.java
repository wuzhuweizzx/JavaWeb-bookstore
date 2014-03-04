package com.zhuwei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhuwei.dao.UserDao;
import com.zhuwei.domain.Privilege;
import com.zhuwei.domain.User;
import com.zhuwei.exception.DaoException;
import com.zhuwei.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	
	public void add(User user){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id, username, password, phone, address, email) values(?,?,?,?,?,?)";
			Object[] params = new Object[]{user.getId(), user.getUsername(), user.getPassword(), user.getPhone(),user.getAddress(),user.getEmail()};	
			runner.update(sql,params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public User find(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			Object[] params = new Object[]{id};
			return runner.query(sql, new BeanHandler(User.class),params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public  User find(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object[] params = new Object[]{username, password};	
			return runner.query(sql, new BeanHandler(User.class), params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public List<Privilege> getPrivileges(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select p.* from user_privilege up, privilege p where up.user_id=? and up.privilege_id = p.id ";
			
			return runner.query(sql, new BeanListHandler(Privilege.class), id);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
