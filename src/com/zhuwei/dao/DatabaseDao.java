package com.zhuwei.dao;

import java.util.List;

import com.zhuwei.domain.Database;

public interface DatabaseDao {

	void add(Database db);

	Database find(String id);

	List<Database> getAll();

	void delete(String id);
	
	int getTotalRecord();
}