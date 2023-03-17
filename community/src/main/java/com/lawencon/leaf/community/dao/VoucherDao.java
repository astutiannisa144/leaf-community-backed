package com.lawencon.leaf.community.dao;

import java.math.BigDecimal;
import java.sql.Date;
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

	public Optional<Voucher> getByCode(String code) {
		Voucher voucher = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT id,voucher_code,discount_price,minimum_purchase,expired_date,ver,is_active ");
			sql.append("FROM t_voucher ");
			sql.append("WHERE voucher_code=:code");
			
			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("code", code).getSingleResult();

			if (result != null) {
				voucher = new Voucher();
				final Object[] objArr = (Object[]) result;

				voucher.setId(objArr[0].toString());
				voucher.setVoucherCode(objArr[1].toString());
				voucher.setDiscountPrice(new BigDecimal( objArr[2].toString()));

			
				voucher.setMinimumPurchase(new BigDecimal( objArr[3].toString()));
			
				voucher.setExpiredDate(Date.valueOf(objArr[5].toString()).toLocalDate());
				voucher.setVer(Integer.valueOf(objArr[5].toString()) );
				voucher.setIsActive(Boolean.valueOf(objArr[3].toString()) );
			}
		} catch (Exception e) {
		}
		
		return Optional.ofNullable(voucher) ;
	}
}
