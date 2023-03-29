package com.lawencon.leaf.community.pojo.user.premium;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PojoUserPremiumRes {
	private String id;
	private String memberId;
	private String memberName;
	private String premiumId;
	private String premiumName;
	private LocalDate expireDate;
	private Integer ver;
	private Boolean isActive;
	private BigDecimal price;
	
	
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPremiumId() {
		return premiumId;
	}
	public void setPremiumId(String premiumId) {
		this.premiumId = premiumId;
	}
	public String getPremiumName() {
		return premiumName;
	}
	public void setPremiumName(String premiumName) {
		this.premiumName = premiumName;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
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
	
	
}
