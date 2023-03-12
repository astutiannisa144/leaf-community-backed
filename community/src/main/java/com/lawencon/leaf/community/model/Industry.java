package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_industry", uniqueConstraints = {
		@UniqueConstraint(name = "industry_bk", columnNames = { "industryCode" }) })
public class Industry extends BaseEntity {

	@Column(length = 10, nullable = false)
	private String industryCode;

	@Column(length = 30, nullable = false)
	private String industryName;

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

}
