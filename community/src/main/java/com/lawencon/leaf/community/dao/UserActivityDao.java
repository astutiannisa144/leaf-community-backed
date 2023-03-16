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
		str.append("FROM t_user_activity ");
		str.append("ORDER BY created_at DESC ");
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
	
	
	public Optional<UserActivity> getByCode(String code) {

		UserActivity userActivity = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, invoice_code ");
			sql.append("FROM t_user_activity  ");
			sql.append("WHERE invoice_code=:code  ");
			sql.append("AND is_active = TRUE");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("code", code).getSingleResult();

			if (result != null) {
				userActivity = new UserActivity();
				final Object[] objArr = (Object[]) result;

				userActivity.setId(objArr[0].toString());
				userActivity.setInvoiceCode(objArr[1].toString());
			}

		} catch (Exception e) {
			
		}

		return Optional.ofNullable(userActivity);

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
	public List<UserActivity> getAllByActivityTypeNotPurchase(String typeCode) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("INNER JOIN t_activity b ON a.activity_id=b.id ");
		str.append("INNER JOIN t_activity_type c ON b.activity_type_id=c.id ");
		str.append("WHERE c.activity_type_code =:typeCode ");
		str.append("AND a.is_approve iS NULL ");
		str.append("ORDER BY a.created_at DESC ");
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
