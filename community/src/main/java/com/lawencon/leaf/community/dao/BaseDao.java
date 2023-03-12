package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.base.AbstractJpaDao;

public abstract class BaseDao<T> extends AbstractJpaDao {
	abstract List<T> getAll();

	abstract Optional<T> getById(String id);
	abstract Optional<T> getByIdAndDetach(String id);

}
