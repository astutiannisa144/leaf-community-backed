package com.lawencon.leaf.community.pojo.user.activity;

import java.math.BigDecimal;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;
import com.lawencon.leaf.community.pojo.user.voucher.PojoUserVoucherReq;

public class PojoUserActivityReq {
	private String id;
	private String activityId;
	private PojoFileReqInsert file;
	private PojoUserVoucherReq userVoucher;
	private Boolean isApprove;
	private BigDecimal totalPrice;
	private Integer ver;
	private Boolean isActive;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public PojoFileReqInsert getFile() {
		return file;
	}
	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
	public PojoUserVoucherReq getUserVoucher() {
		return userVoucher;
	}
	public void setUserVoucher(PojoUserVoucherReq userVoucher) {
		this.userVoucher = userVoucher;
	}
	
}
