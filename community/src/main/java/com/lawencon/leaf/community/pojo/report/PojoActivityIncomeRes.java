package com.lawencon.leaf.community.pojo.report;

import java.math.BigDecimal;

public class PojoActivityIncomeRes {

	private String title;
	private String activityType;
	private BigDecimal totalIncome;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
