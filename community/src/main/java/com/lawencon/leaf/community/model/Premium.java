package com.lawencon.leaf.community.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_premium", uniqueConstraints = {
		@UniqueConstraint(name = "premium_bk", columnNames = { "premiumCode" }) })
public class Premium extends BaseEntity {

	@Column(nullable = false)
	private Integer duration;

	@Column(length = 36, nullable = false)
	private String premiumName;

	@Column(nullable = false, length = 36)
	private String premiumCode;

	private BigDecimal price;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
