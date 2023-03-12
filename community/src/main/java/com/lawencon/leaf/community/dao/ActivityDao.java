package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Activity;

@Repository
public class ActivityDao extends BaseDao<Activity> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByCategory(String id) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity ");
		str.append("WHERE category_id = :id");
		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("id", id)
				.getResultList();
		return activities;
	}
	
	@Override
	public Optional<Activity> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Activity.class, id));
	}

	@Override
	public Optional<Activity> getById(String id) {

		return Optional.ofNullable(super.getById(Activity.class, id));

	}

}
