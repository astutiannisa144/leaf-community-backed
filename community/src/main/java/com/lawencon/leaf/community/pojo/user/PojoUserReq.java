package com.lawencon.leaf.community.pojo.user;

import com.lawencon.leaf.community.pojo.profile.PojoProfileReq;

public class PojoUserReq {
	private String id;
	private String email;
	private String pass;
	private String verificationCode;
	private String roleId;
	private PojoProfileReq profile;
	private String provider;
	private Integer ver;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public PojoProfileReq getProfile() {
		return profile;
	}
	public void setProfile(PojoProfileReq profile) {
		this.profile = profile;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	
	
}
