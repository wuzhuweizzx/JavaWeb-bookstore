package com.zhuwei.dao;

import java.util.List;

import com.zhuwei.domain.Category;

public interface CategoryDao {

	void add(Category category);

	Category find(String id);

	List<Category> getAll();

}