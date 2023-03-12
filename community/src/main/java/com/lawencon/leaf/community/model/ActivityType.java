package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_activity_type", uniqueConstraints = {
		@UniqueConstraint(name = "activity_type_bk", columnNames = { "activityTypeCode" }) })
public class ActivityType extends BaseEntity {

	@Column(nullable = false, length = 10)
	private String activityTypeCode;

	@Column(nullable = false, length = 30)
	private String activityTypeName;

	public String getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

}
