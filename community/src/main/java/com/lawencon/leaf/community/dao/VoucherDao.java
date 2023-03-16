package com.lawencon.leaf.community.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.Voucher;

@Repository
public class VoucherDao extends BaseDao<Voucher> {
	@SuppressWarnings("unchecked")
	@Override
	public List<Voucher> getAll() {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_voucher ");
		str.append("WHERE expired_date>NOW()");

		final List<Voucher> vouchers = ConnHandler.getManager().createNativeQuery(str.toString(), Voucher.class)
				.getResultList();
		return vouchers;
	}
	
	@Override
	public Optional<Voucher> getByIdAndDetach(String id) {
		return Optional.ofNullable(super.getByIdAndDetach(Voucher.class, id));
	}

	@Override
	public Optional<Voucher> getById(String id) {

		return Optional.ofNullable(super.getById(Voucher.class, id));

	}

	public Voucher getByCode(String code) {
		final StringBuilder str = new StringBuilder();
		str.append("SELECT * ");
		str.append("FROM t_voucher ");
		str.append("WHERE voucher_code=:code");

		@SuppressWarnings("unchecked")
		final List<Voucher> vouchers = ConnHandler.getManager().createNativeQuery(str.toString(), Voucher.class)
				.getResultList();
		return vouchers.get(0);
	}
}
