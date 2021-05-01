package com.shopapi.revature.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public boolean add(T t);
	public List<T> getAll();
	public boolean update(T t);
	public boolean delete(T t);

}
