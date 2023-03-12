package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.ActivityType;

@Repository
public class ActivityTypeDao extends BaseDao<ActivityType> {

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityType> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity_type");
		final List<ActivityType> activities = ConnHandler.getManager().createNativeQuery(str.toString(), ActivityType.class).getResultList();
		return activities;
	}

	@Override
	public Optional<ActivityType> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(ActivityType.class, id));
	}

	@Override
	public Optional<ActivityType> getById(String id) {
		return Optional.ofNullable(super.getById(ActivityType.class, id));
	}
	
}
