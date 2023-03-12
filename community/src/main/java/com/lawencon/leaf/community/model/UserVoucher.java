package com.lawencon.leaf.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_voucher", uniqueConstraints = {
		@UniqueConstraint(name = "user_voucher_ck", columnNames = { "member_id", "voucher_id" }) })
public class UserVoucher extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "voucher_id", nullable = false)
	private Voucher voucher;

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

}
