package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.UserActivity;

@Repository
public class UserActivityDao extends BaseDao<UserActivity> {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserActivity> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity");

		final List<UserActivity> userActivitys = ConnHandler.getManager().createNativeQuery(str.toString(), UserActivity.class).getResultList();
		return userActivitys;
	}

	@Override
	public Optional<UserActivity> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(UserActivity.class, id));
	}

	@Override
	public Optional<UserActivity> getById(String id) {

		return Optional.ofNullable(super.getById(UserActivity.class, id));

	}

}
