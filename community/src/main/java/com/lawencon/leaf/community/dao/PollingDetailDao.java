package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.PollingDetail;

@Repository
public class PollingDetailDao extends BaseDao<PollingDetail> {

	@Override
	List<PollingDetail> getAll() {
		return null;
	}

	@Override
	Optional<PollingDetail> getById(String id) {
		return Optional.empty();
	}

	@Override
	Optional<PollingDetail> getByIdAndDetach(String id) {
		return Optional.empty();
	}
	
	@SuppressWarnings("unchecked")
	public List<PollingDetail> getAllByPolling(String id){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_polling_detail ");
		sql.append("WHERE polling_id = :id ");
		
		final List<PollingDetail> postList = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), PollingDetail.class)
				.setParameter("id", id)
				.getResultList();

		return postList;
	}

}
