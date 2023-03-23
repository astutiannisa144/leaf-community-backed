package com.lawencon.leaf.community.pojo.report;

public class PojoParticipantRes {

	private String fullName;
	private PojoActivityTypeRes activityType;
	private PojoActivityRes activity;
	private String dateStart;
	private Long totalParticipant;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public PojoActivityTypeRes getActivityType() {
		return activityType;
	}

	public void setActivityType(PojoActivityTypeRes activityType) {
		this.activityType = activityType;
	}

	public PojoActivityRes getActivity() {
		return activity;
	}

	public void setActivity(PojoActivityRes activity) {
		this.activity = activity;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public Long getTotalParticipant() {
		return totalParticipant;
	}

	public void setTotalParticipant(Long totalParticipant) {
		this.totalParticipant = totalParticipant;
	}

}
