package com.lawencon.leaf.community.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_premium")
public class UserPremium extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "premium_id", nullable = false)
	private Premium premium;

	private LocalDate expireDate;

	@OneToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	public Premium getPremium() {
		return premium;
	}

	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
