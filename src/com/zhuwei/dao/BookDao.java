package com.zhuwei.dao;


import java.util.List;

import com.zhuwei.domain.Book;

public interface BookDao {

	void add(Book book);

	Book find(String id);
	
	public List<Book> getPageDate(int startindex, int pagesize );
	public List<Book> getPageDate(int startindex, int pagesize, String category_id );
	
	public int getTotalRecord();
	public int getTotalRecord(String category_id);
	

}