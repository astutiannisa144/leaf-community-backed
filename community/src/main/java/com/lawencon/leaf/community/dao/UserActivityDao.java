package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.UserActivity;

@Repository
public class UserActivityDao extends BaseDao<UserActivity> {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserActivity> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity");

		final List<UserActivity> userActivitys = ConnHandler.getManager().createNativeQuery(str.toString(), UserActivity.class).getResultList();
		return userActivitys;
	}

	@Override
	public Optional<UserActivity> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(UserActivity.class, id));
	}

	@Override
	public Optional<UserActivity> getById(String id) {

		return Optional.ofNullable(super.getById(UserActivity.class, id));

	}
	
	@SuppressWarnings("unchecked")
	public List<UserActivity> getAllByActivityType(String typeCode) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("INNER JOIN t_activity b ON a.activity_id=b.id ");
		str.append("INNER JOIN t_activity_type c ON b.activity_type_id=c.id ");
		str.append("WHERE c.activity_type_code =:typeCode");
		final List<UserActivity> userActivitys = ConnHandler.getManager().createNativeQuery(str.toString(), UserActivity.class)
		.setParameter("typeCode", typeCode)
		.getResultList();
		return userActivitys;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserActivity> getAllByActivityByTypeAndMember(String typeCode,String memberId) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("INNER JOIN t_activity b ON a.activity_id=b.id ");
		str.append("INNER JOIN t_activity_type c ON b.activity_type_id=c.id ");
		str.append("WHERE c.activity_type_code =:typeCode ");
		str.append("AND a.member_id =:memberId");
		final List<UserActivity> userActivitys = ConnHandler.getManager().createNativeQuery(str.toString(), UserActivity.class)
		.setParameter("typeCode", typeCode)
		.setParameter("memberId", memberId)
		.getResultList();
		return userActivitys;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserActivity> getAllByActivityPurchased(String memberId) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("AND a.member_id =:memberId ");
		final List<UserActivity> userActivitys = ConnHandler.getManager().createNativeQuery(str.toString(), UserActivity.class)
		.setParameter("memberId", memberId)
		.getResultList();
		return userActivitys;
	}
}
