package com.zhuwei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhuwei.dao.CategoryDao;
import com.zhuwei.domain.Category;
import com.zhuwei.exception.DaoException;
import com.zhuwei.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	public void add(Category category){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id, name, description) values(?,?,?)";
			Object[] params = new Object[]{category.getId(), category.getName(), category.getDescription() };
			runner.update(sql, params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public Category find(String id){
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";
			return runner.query(sql, new BeanHandler(Category.class));
		} catch (Exception e) {
			throw new DaoException(e);
		}
	} 
	
	public List<Category> getAll(){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category ";
			return runner.query(sql, new BeanListHandler(Category.class));
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
