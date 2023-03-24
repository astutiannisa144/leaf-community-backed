package com.lawencon.leaf.community.pojo.report;

import java.math.BigDecimal;

public class PojoMemberIncomeRes {

	private String fullName;
	private String activityType;
	private BigDecimal totalIncome;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

}
