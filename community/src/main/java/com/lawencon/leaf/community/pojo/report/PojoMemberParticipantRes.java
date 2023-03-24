package com.lawencon.leaf.community.pojo.report;

public class PojoMemberParticipantRes {

	private String fullName;
	private String provider;
	private String title;
	private String activityTypeName;
	private String dateStart;
	private Long totalParticipant;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
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
