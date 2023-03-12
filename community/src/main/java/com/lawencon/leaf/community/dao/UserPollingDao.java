package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.leaf.community.model.UserPolling;

@Repository
public class UserPollingDao extends BaseDao<UserPolling> {

	@Override
	List<UserPolling> getAll() {
		return null;
	}

	@Override
	Optional<UserPolling> getById(String id) {
		return Optional.empty();
	}

	@Override
	Optional<UserPolling> getByIdAndDetach(String id) {
		return Optional.empty();
	}

}
