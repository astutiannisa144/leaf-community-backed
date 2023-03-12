package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Category;

@Repository
public class CategoryDao extends BaseDao<Category> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * FROM t_category");
		
		final List<Category> categories = ConnHandler.getManager().createNativeQuery(str.toString(), Category.class).getResultList();
		return categories;
	}

	@Override
	public Optional<Category> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Category.class, id));
	}

	@Override
	public Optional<Category> getById(String id) {
		return Optional.ofNullable(super.getById(Category.class, id));
	}
	
}