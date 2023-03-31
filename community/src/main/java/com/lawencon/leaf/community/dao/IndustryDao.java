package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Industry;

@Repository
public class IndustryDao extends BaseDao<Industry> {
	@SuppressWarnings("unchecked")
	@Override
	public List<Industry> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_industry");

		final List<Industry> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Industry.class).getResultList();
		return activities;
	}
	@SuppressWarnings("unchecked")
	public Optional<Industry> getByBk(String code) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_industry ");
		str.append("WHERE industry_code=:code");

		final List<Industry> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Industry.class)
				.setParameter("code", code)
				.getResultList();
		return Optional.ofNullable(activities.get(0));
	}
	@Override
	public Optional<Industry> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Industry.class, id));
	}

	@Override
	public Optional<Industry> getById(String id) {

		return Optional.ofNullable(super.getById(Industry.class, id));

	}
}
