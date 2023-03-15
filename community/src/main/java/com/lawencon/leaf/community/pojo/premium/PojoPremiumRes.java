package com.lawencon.leaf.community.pojo.premium;

import java.math.BigDecimal;

public class PojoPremiumRes {
	private String premiumName;
	private String premiumCode;
	private Integer ver;
	private String id;
	private Integer duration;
	private BigDecimal price; 
	private Boolean isActive;
	
	public String getPremiumName() {
		return premiumName;
	}
	public void setPremiumName(String premiumName) {
		this.premiumName = premiumName;
	}
	public String getPremiumCode() {
		return premiumCode;
	}
	public void setPremiumCode(String premiumCode) {
		this.premiumCode = premiumCode;
	}
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
