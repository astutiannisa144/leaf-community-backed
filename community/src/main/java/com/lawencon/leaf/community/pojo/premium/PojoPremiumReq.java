package com.lawencon.leaf.community.pojo.premium;

import java.math.BigDecimal;

public class PojoPremiumReq {
	private String premiumName;
	private Integer ver;
	private String id;
	private Integer duration;
	private BigDecimal price; 	
	
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

	public String getPremiumName() {
		return premiumName;
	}

	public void setPremiumName(String premiumName) {
		this.premiumName = premiumName;
	}
}
