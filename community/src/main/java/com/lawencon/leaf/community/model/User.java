package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user", uniqueConstraints = {
		@UniqueConstraint(name = "user_bk", columnNames = { "email" }) })
public class User extends BaseEntity {
	
	@Column(length = 30, nullable = false)
	private String email;

	@Column(name = "pass", columnDefinition = "text", nullable = false)
	private String pass;
	
	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToOne
	@JoinColumn(name = "profile_id", nullable = false)
	private Profile profile;
	
	private String verificationCode;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
}
