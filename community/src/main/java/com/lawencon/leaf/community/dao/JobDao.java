package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Job;

@Repository
public class JobDao extends BaseDao<Job> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_job");

		final List<Job> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Job.class).getResultList();
		return activities;
	}

	@Override
	public Optional<Job> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Job.class, id));
	}

	@Override
	public Optional<Job> getById(String id) {

		return Optional.ofNullable(super.getById(Job.class, id));

	}
}
