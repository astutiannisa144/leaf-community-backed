package com.lawencon.leaf.community.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.ConnHandler;

@Repository
public class UserVoucherDao  {
	
	public Optional<String> getRefByMemberAndVoucher(String memberId,String voucherId){
		
		final StringBuilder str = new StringBuilder();
		str.append("SELECT id ");
		str.append("FROM t_user_voucher ");
		str.append("WHERE member_id=:memberId ");
		str.append("AND voucher_id=:voucherId ");
		final String result = (String) ConnHandler.getManager().createNativeQuery(str.toString())
				.setParameter("memberId", memberId)
				.setParameter("voucherId", voucherId)
				.getSingleResult();
		return Optional.ofNullable(result);
		
	}
}
