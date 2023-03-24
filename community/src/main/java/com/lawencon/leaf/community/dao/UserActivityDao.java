package com.lawencon.leaf.community.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.UserActivity;
import com.lawencon.leaf.community.pojo.report.PojoActivityIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberIncomeRes;
import com.lawencon.leaf.community.pojo.report.PojoMemberParticipantRes;
import com.lawencon.leaf.community.pojo.report.PojoActivityParticipantRes;
import com.lawencon.leaf.community.util.DateUtil;

@Repository
public class UserActivityDao extends BaseDao<UserActivity> {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserActivity> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity ");
		str.append("ORDER BY created_at DESC ");
		final List<UserActivity> userActivitys = ConnHandler.getManager()
				.createNativeQuery(str.toString(), UserActivity.class).getResultList();
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

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString()).setParameter("code", code)
					.getSingleResult();

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

		final List<UserActivity> userActivitys = ConnHandler.getManager()
				.createNativeQuery(str.toString(), UserActivity.class).setParameter("typeCode", typeCode)
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
		final List<UserActivity> userActivitys = ConnHandler.getManager()
				.createNativeQuery(str.toString(), UserActivity.class).setParameter("typeCode", typeCode)
				.getResultList();
		return userActivitys;
	}

	@SuppressWarnings("unchecked")
	public List<UserActivity> getAllByActivityByTypeAndMember(String typeCode, String memberId) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("INNER JOIN t_activity b ON a.activity_id=b.id ");
		str.append("INNER JOIN t_activity_type c ON b.activity_type_id=c.id ");
		str.append("WHERE c.activity_type_code =:typeCode ");
		str.append("AND a.member_id =:memberId");
		final List<UserActivity> userActivitys = ConnHandler.getManager()
				.createNativeQuery(str.toString(), UserActivity.class).setParameter("typeCode", typeCode)
				.setParameter("memberId", memberId).getResultList();
		return userActivitys;
	}

	@SuppressWarnings("unchecked")
	public List<UserActivity> getAllByActivityPurchased(String memberId) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_user_activity a ");
		str.append("AND a.member_id =:memberId ");
		final List<UserActivity> userActivitys = ConnHandler.getManager()
				.createNativeQuery(str.toString(), UserActivity.class).setParameter("memberId", memberId)
				.getResultList();
		return userActivitys;
	}
	
	@SuppressWarnings("unchecked")
	public List<PojoActivityParticipantRes> getActivityParticipants(String dateStart, String dateEnd) {

		final List<PojoActivityParticipantRes> reportList = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.activity_type_name, a.title, ");
		sql.append("(SELECT MIN(schedule_date) FROM t_schedule WHERE activity_id = a.id), ");
		sql.append("COUNT(ua.id) FROM t_user_activity ua ");
		sql.append("INNER JOIN t_activity a ON a.id = ua.activity_id  ");
		sql.append("INNER JOIN t_activity_type t ON t.id = a.activity_type_id ");
		sql.append("WHERE ua.is_approved = true ");
		sql.append("GROUP BY t.activity_type_name, a.title, a.id ");
		sql.append("HAVING (SELECT MIN(schedule_date) FROM t_schedule WHERE activity_id = a.id)  ");
		sql.append("BETWEEN DATE(:date_start) AND DATE(:date_end) ");

		final List<Object> result = ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("date_start", dateStart).setParameter("date_end", dateEnd).getResultList();

		for (int i = 0; i < result.size(); i++) {

			final PojoActivityParticipantRes report = new PojoActivityParticipantRes();
			final Object[] objArr = (Object[]) result.get(i);

			report.setTitle(objArr[1].toString());
			report.setActivityTypeName(objArr[0].toString());
			report.setDateStart(DateUtil.isoToDate(objArr[2].toString()));
			report.setTotalParticipant(Long.valueOf(objArr[3].toString()));

			reportList.add(report);
		}

		return reportList;
	}

	@SuppressWarnings("unchecked")
	public List<PojoMemberParticipantRes> getMemberParticipants(String dateStart, String dateEnd) {

		final List<PojoMemberParticipantRes> reportList = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.full_name, a.provider, t.activity_type_name, a.title, ");
		sql.append("(SELECT MIN(schedule_date) FROM t_schedule WHERE activity_id = a.id), ");
		sql.append("COUNT(ua.id) FROM t_user_activity ua ");
		sql.append("INNER JOIN t_activity a ON a.id = ua.activity_id  ");
		sql.append("INNER JOIN t_activity_type t ON t.id = a.activity_type_id ");
		sql.append("INNER JOIN t_user u ON u.id = a.member_id  ");
		sql.append("INNER JOIN t_profile p ON p.id = u.profile_id  ");
		sql.append("WHERE ua.is_approved = true ");
		sql.append("GROUP BY p.full_name, a.provider, t.activity_type_name, a.id, a.title ");
		sql.append("HAVING (SELECT MIN(schedule_date) FROM t_schedule WHERE activity_id = a.id)  ");
		sql.append("BETWEEN DATE(:date_start) AND DATE(:date_end) ");

		final List<Object> result = ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("date_start", dateStart).setParameter("date_end", dateEnd).getResultList();

		for (int i = 0; i < result.size(); i++) {

			final PojoMemberParticipantRes report = new PojoMemberParticipantRes();
			final Object[] objArr = (Object[]) result.get(i);

			report.setFullName(objArr[0].toString());
			report.setProvider(objArr[1].toString());
			report.setTitle(objArr[3].toString());
			report.setActivityTypeName(objArr[2].toString());
			report.setDateStart(DateUtil.isoToDate(objArr[4].toString()));
			report.setTotalParticipant(Long.valueOf(objArr[5].toString()));

			reportList.add(report);
		}

		return reportList;
	}

	@SuppressWarnings("unchecked")
	public List<PojoActivityIncomeRes> getEventIncome(String dateStart, String dateEnd) {
		List<PojoActivityIncomeRes> reportList = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.title, t.activity_type_name, COUNT(ua.id)*a.price income ");
		sql.append("FROM t_user_activity ua ");
		sql.append("INNER JOIN t_activity a ON a.id = ua.activity_id ");
		sql.append("INNER JOIN t_activity_type t ON t.id = a.activity_type_id ");
		sql.append("WHERE ua.is_approved = true AND ua.updated_at BETWEEN DATE(:date_start) AND DATE(:date_end) ");
		sql.append("GROUP BY t.activity_type_name, a.title, a.price ");

		final List<Object> result = ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("date_start", dateStart).setParameter("date_end", dateEnd).getResultList();

		for (int i = 0; i < result.size(); i++) {
			final PojoActivityIncomeRes report = new PojoActivityIncomeRes();
			final Object[] objArr = (Object[]) result.get(i);

			report.setTitle(objArr[0].toString());
			report.setActivityType(objArr[1].toString());
			report.setTotalIncome(new BigDecimal(objArr[2].toString()));
			reportList.add(report);
		}

		return reportList;
	}

	@SuppressWarnings("unchecked")
	public List<PojoMemberIncomeRes> getMemberIncome(String dateStart, String dateEnd) {
		List<PojoMemberIncomeRes> reportList = new ArrayList<>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.full_name, t.activity_type_name, COUNT(ua.id)*a.price income ");
		sql.append("FROM t_user_activity ua ");
		sql.append("INNER JOIN t_activity a ON a.id = ua.activity_id ");
		sql.append("INNER JOIN t_activity_type t ON t.id = a.activity_type_id ");
		sql.append("INNER JOIN t_user u ON u.id = a.member_id ");
		sql.append("INNER JOIN t_profile p ON p.id = u.profile_id ");
		sql.append("WHERE ua.is_approved = true AND ua.updated_at BETWEEN DATE(:date_start) AND DATE(:date_end) ");
		sql.append("GROUP BY p.full_name, t.activity_type_name, a.price ");

		final List<Object> result = ConnHandler.getManager().createNativeQuery(sql.toString())
				.setParameter("date_start", dateStart).setParameter("date_end", dateEnd).getResultList();

		for (int i = 0; i < result.size(); i++) {
			final PojoMemberIncomeRes report = new PojoMemberIncomeRes();
			final Object[] objArr = (Object[]) result.get(i);

			report.setFullName(objArr[0].toString());
			report.setActivityType(objArr[1].toString());
			report.setTotalIncome(new BigDecimal(objArr[2].toString()));

			reportList.add(report);
		}

		return reportList;
	}

}
