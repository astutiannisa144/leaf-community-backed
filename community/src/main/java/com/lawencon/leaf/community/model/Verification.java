package com.lawencon.leaf.community.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_verification", uniqueConstraints = {
		@UniqueConstraint(name = "verificaton_bk", columnNames = { "verificationCode" }) })
public class Verification extends BaseEntity {

	@Column(nullable = false, length = 30)
	private String email;

	@Column(nullable = false, length = 10)
	private String verificationCode;

	@Column(nullable = false)
	private LocalDateTime expiredTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

}
