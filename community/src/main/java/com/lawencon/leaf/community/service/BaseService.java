package com.lawencon.leaf.community.service;

import java.util.List;

public abstract class BaseService<T> {

abstract T getById(String id);
	
abstract List<T> getAll();

}
