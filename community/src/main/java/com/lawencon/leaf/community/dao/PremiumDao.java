package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Premium;

public class PremiumDao extends BaseDao<Premium> {
	@SuppressWarnings("unchecked")
	@Override
	public List<Premium> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_premium");

		final List<Premium> premiums = ConnHandler.getManager().createNativeQuery(str.toString(), Premium.class)
				.getResultList();
		return premiums;
	}
	
	@Override
	public Optional<Premium> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Premium.class, id));
	}

	@Override
	public Optional<Premium> getById(String id) {

		return Optional.ofNullable(super.getById(Premium.class, id));

	}
}
