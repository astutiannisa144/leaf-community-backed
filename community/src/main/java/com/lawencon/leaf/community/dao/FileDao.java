package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.File;

@Repository
public class FileDao extends BaseDao<File> {

	@SuppressWarnings("unchecked")
	@Override
	public List<File> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_file");

		final List<File> activities = ConnHandler.getManager().createNativeQuery(str.toString(), File.class)
				.getResultList();
		return activities;
	}

	@Override
	public Optional<File> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(File.class, id));
	}

	@Override
	public Optional<File> getById(String id) {

		return Optional.ofNullable(super.getById(File.class, id));

	}

}
