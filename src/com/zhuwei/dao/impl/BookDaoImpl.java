package com.zhuwei.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zhuwei.dao.BookDao;
import com.zhuwei.domain.Book;
import com.zhuwei.exception.DaoException;
import com.zhuwei.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {
	
	public void add(Book book){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into book(id, name, author, price, image, description, category_id) values(?,?,?,?,?,?,?)";
			Object[] params = new Object[]{book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getImage(), book.getDescription(), book.getCategory_id()};	
			runner.update(sql,params);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public Book find(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where id=?";
			return runner.query(sql, new BeanHandler(Book.class), id);
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public List<Book> getPageDate(int startindex, int pagesize ){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book limit ?,?";
			Object[] params = new Object[]{startindex, pagesize};
			return runner.query(sql, new BeanListHandler(Book.class), params);
		}catch (Exception e) {
			throw new DaoException(e);
		}		
	}
	
	public int getTotalRecord(){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book";
			
			long totalrecord =  runner.query(sql, new ScalarHandler());
			return (int) totalrecord;
		}catch (Exception e) {
			throw new DaoException(e);
		}	
	}
	
	//Get Page Data Under Category
	public List<Book> getPageDate(int startindex, int pagesize, String category_id ){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where category_id=? limit ?,?";
			Object[] params = new Object[]{category_id,startindex, pagesize};
			return runner.query(sql, new BeanListHandler(Book.class), params);
		}catch (Exception e) {
			throw new DaoException(e);
		}		
	}
	
	public int getTotalRecord(String category_id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select count(*) from book where category_id=?";
			
			long totalrecord =  runner.query(sql, new ScalarHandler(), category_id);
			return (int) totalrecord;
		}catch (Exception e) {
			throw new DaoException(e);
		}	
	}

}
