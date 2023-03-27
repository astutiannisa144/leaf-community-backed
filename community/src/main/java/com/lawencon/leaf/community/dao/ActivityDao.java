package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Activity;

@Repository
public class ActivityDao extends BaseDao<Activity> {

	@SuppressWarnings("unchecked")
	
	public List<Activity> getAll(int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity ");
		str.append("ORDER BY created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByCategory(String id,int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity ");
		str.append("WHERE category_id = :id ");
		str.append("ORDER BY created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("id", id)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByType(String typeCode,int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByTypeAndCategory(String typeCode, String categoryId,int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("AND a.category_id = :categoryId ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("categoryId", categoryId)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByTypeAndMember(String typeCode, String memberId,int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("AND a.member_id = :memberId ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("memberId", memberId)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllByTypeAndPurchased(String typeCode, String memberId,int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_user_activity c ON c.activity_id=a.id ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("AND c.member_id = :memberId ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("memberId", memberId)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}
	@Override
	public Optional<Activity> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Activity.class, id));
	}

	@Override
	public Optional<Activity> getById(String id) {

		return Optional.ofNullable(super.getById(Activity.class, id));

	}

	@Override
	List<Activity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getAllByTypeCategoryAndMember(String typeCode, String categoryId, String memberId,
			int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("AND a.category_id = :categoryId ");
		str.append("AND a.member_id = :memberId ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("memberId", memberId)
				.setParameter("categoryId", categoryId)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}

	@SuppressWarnings("unchecked")
	public List<Activity> getAllByTypeCategoryAndPurchased(String typeCode, String categoryId,
			String memberId, int limit, int offset) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_activity a ");
		str.append("INNER JOIN t_user_activity c ON c.activity_id=a.id ");
		str.append("INNER JOIN t_activity_type b ON a.activity_type_id=b.id ");
		str.append("WHERE b.activity_type_code = :typeCode ");
		str.append("AND a.category_id = :categoryId ");
		str.append("AND c.member_id = :memberId ");
		str.append("ORDER BY a.created_at DESC ");

		final List<Activity> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Activity.class)
				.setParameter("memberId", memberId)
				.setParameter("categoryId", categoryId)
				.setParameter("typeCode", typeCode)
				.setFirstResult((offset - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		return activities;
	}

	public static Optional<Activity> getByCode(String code) {
		Activity activity = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, activity_code ");
			sql.append("FROM t_activity  ");
			sql.append("WHERE activity_code=:code  ");
			sql.append("AND is_active = TRUE");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("code", code).getSingleResult();

			if (result != null) {
				activity = new Activity();
				final Object[] objArr = (Object[]) result;

				activity.setId(objArr[0].toString());
				activity.setActivityCode(objArr[1].toString());
			}

		} catch (Exception e) {
		}

		return Optional.ofNullable(activity);
	}

}
