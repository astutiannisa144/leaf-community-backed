package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
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

	public Long countTotalPolling(String id) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM t_user_polling ");
		sql.append("WHERE polling_id = :id ");

		final Long count = Long.valueOf(ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("id", id).getSingleResult().toString());

		return count;
	}

	public Long countPercentage(String id) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM t_user_polling ");
		sql.append("WHERE polling_detail_id = :id ");

		final Long count = Long.valueOf(ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("id", id).getSingleResult().toString());

		return count;
	}

	public Optional<String> getId(String id) {

		String userPollingId = null;

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT id FROM t_user_polling ");
			sql.append("WHERE member_id = :id ");

			userPollingId = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(userPollingId);
	}

}
