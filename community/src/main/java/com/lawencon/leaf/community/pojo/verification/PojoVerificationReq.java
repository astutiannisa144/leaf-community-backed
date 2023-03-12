package com.lawencon.leaf.community.pojo.verification;

public class PojoVerificationReq {
	private String email;
	private Integer ver;
	private String id;
	
	
	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

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


}
