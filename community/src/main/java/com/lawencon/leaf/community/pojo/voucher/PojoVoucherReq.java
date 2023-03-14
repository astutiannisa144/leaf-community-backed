package com.lawencon.leaf.community.pojo.voucher;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PojoVoucherReq {
	private String voucherCode;
	private Integer ver;
	private String id;
	private BigDecimal discountPrice;
	private LocalDate expiredDate;
	private BigDecimal minimumPurchase;
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
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
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public LocalDate getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	public BigDecimal getMinimumPurchase() {
		return minimumPurchase;
	}
	public void setMinimumPurchase(BigDecimal minimumPurchase) {
		this.minimumPurchase = minimumPurchase;
	} 	
	
	
}
