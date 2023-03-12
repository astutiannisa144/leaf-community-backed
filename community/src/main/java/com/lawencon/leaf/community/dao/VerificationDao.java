package com.lawencon.leaf.community.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Verification;

@Repository
public class VerificationDao extends BaseDao<Verification> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Verification> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_verification");

		final List<Verification> activities = ConnHandler.getManager().createNativeQuery(str.toString(), Verification.class)
				.getResultList();
		return activities;
	}

	@Override
	public Optional<Verification> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Verification.class, id));
	}

	@Override
	public Optional<Verification> getById(String id) {

		return Optional.ofNullable(super.getById(Verification.class, id));

	}
	
	public Optional<Verification> getBycode(String code,String email) {

		Verification verification = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, email, verification_code, expired_time, is_active ");
			sql.append("FROM t_verification  ");
			sql.append("WHERE verification_code = :code ");
			sql.append("AND email = :email ");
			sql.append("AND NOW() <=  expired_time ");
			sql.append("AND is_active = TRUE");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("code", code)
					.setParameter("email", email)
					.getSingleResult();

			if (result != null) {
				verification = new Verification();
				final Object[] objArr = (Object[]) result;

				verification.setId(objArr[0].toString());
				verification.setEmail(objArr[1].toString());
				verification.setVerificationCode(objArr[2].toString());
				verification.setExpiredTime(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime() );
				verification.setIsActive(Boolean.valueOf(objArr[4].toString()));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(verification);

	}

}
