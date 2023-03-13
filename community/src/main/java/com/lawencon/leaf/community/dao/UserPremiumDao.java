package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.UserPremium;

@Repository
public class UserPremiumDao extends BaseDao<UserPremium> {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPremium> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_premium");

		final List<UserPremium> activities = ConnHandler.getManager().createNativeQuery(str.toString(), UserPremium.class)
				.getResultList();
		return activities;
	}

	@Override
	public Optional<UserPremium> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(UserPremium.class, id));
	}

	@Override
	public Optional<UserPremium> getById(String id) {

		return Optional.ofNullable(super.getById(UserPremium.class, id));

	}
	
	public Optional<String> getByUser(String memberId) {

		String result=null;
		final StringBuilder str = new StringBuilder();
		str.append("SELECT id ");
		str.append("FROM t_user_premium ");
		str.append("WHERE member_id=:memberId ");
		try {
			result =  ConnHandler.getManager().createNativeQuery(str.toString())
					.setParameter("memberId", memberId)
					.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(result);

	}

}
