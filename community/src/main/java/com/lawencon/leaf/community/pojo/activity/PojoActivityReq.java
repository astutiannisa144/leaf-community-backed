package com.lawencon.leaf.community.pojo.activity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;
import com.lawencon.leaf.community.pojo.schedule.PojoScheduleReq;

public class PojoActivityReq {
	private String id;
	private String activityTypeId;
	private String categoryId;
	private String memberId;
	private String title;
	private String description;
	private String provider;
	private String locationAddress;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private BigDecimal price;
	private PojoFileReqInsert file;
	private Integer ver;
	private List<PojoScheduleReq> schedule;
	
	public String getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}


	public LocalTime getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}
	public LocalTime getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public PojoFileReqInsert getFile() {
		return file;
	}
	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
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
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public List<PojoScheduleReq> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<PojoScheduleReq> schedule) {
		this.schedule = schedule;
	}
	
	
}
