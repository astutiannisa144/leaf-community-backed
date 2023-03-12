package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Role;

@Repository
public class RoleDao extends BaseDao<Role>{
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_role");

		final List<Role> roles = ConnHandler.getManager().createNativeQuery(str.toString(), Role.class).getResultList();
		return roles;
	}

	@Override
	public Optional<Role> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Role.class, id));
	}

	@Override
	public Optional<Role> getById(String id) {

		return Optional.ofNullable(super.getById(Role.class, id));

	}

}
