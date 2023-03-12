package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Profile;

@Repository
public class ProfileDao extends BaseDao<Profile> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_profile");

		final List<Profile> profiles = ConnHandler.getManager().createNativeQuery(str.toString(), Profile.class)
				.getResultList();
		return profiles;
	}
	
	@Override
	public Optional<Profile> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Profile.class, id));
	}

	@Override
	public Optional<Profile> getById(String id) {

		return Optional.ofNullable(super.getById(Profile.class, id));

	}

}
