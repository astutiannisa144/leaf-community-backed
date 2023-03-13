package com.lawencon.leaf.community.pojo.user.premium;

import java.time.LocalDate;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;

public class PojoUserPremiumReq {
	
	private String id;
	private String premiumId;
	private LocalDate expireDate;
	private Integer ver;
	private Boolean isActive;
	private PojoFileReqInsert file;
	
	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPremiumId() {
		return premiumId;
	}

	public void setPremiumId(String premiumId) {
		this.premiumId = premiumId;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public PojoFileReqInsert getFile() {
		return file;
	}

	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
	
	
}
