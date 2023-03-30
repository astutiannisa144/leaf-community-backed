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

		final Long count = Long.valueOf(ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.setParameter("id", id)
				.getSingleResult().toString());

		return count;
	}

	public Optional<String> getId(String uid, String pid) {

		String userPollingId = null;

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT id FROM t_user_polling ");
			sql.append("WHERE polling_id = :pid ");
			sql.append("AND member_id = :uid ");

			userPollingId = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("uid", uid)
					.setParameter("pid", pid)
					.getSingleResult().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(userPollingId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserPolling> getAllByPollingId(String id) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_user_polling ");
		sql.append("WHERE polling_id = :id ");

		final List<UserPolling> userPollingList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), UserPolling.class)
				.setParameter("id", id)
				.getResultList();

		return userPollingList;
	}

}
