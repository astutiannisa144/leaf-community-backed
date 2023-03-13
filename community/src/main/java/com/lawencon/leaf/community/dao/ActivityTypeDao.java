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
	
	
	public Optional<ActivityType> getByCode(String code) {
		ActivityType activityType=null;
		final StringBuilder str = new StringBuilder();
		str.append("SELECT id ");
		str.append("FROM t_activity_type a=");
		str.append("WHERE activity_type_code = :code");

		final Object result = ConnHandler.getManager().createNativeQuery(str.toString())
				.setParameter("code", code).getSingleResult();
		if (result != null) {
			final Object[] objArr = (Object[]) result;

			activityType = new ActivityType();
			activityType.setId(objArr[0].toString());
		}
		return Optional.ofNullable(activityType);
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
