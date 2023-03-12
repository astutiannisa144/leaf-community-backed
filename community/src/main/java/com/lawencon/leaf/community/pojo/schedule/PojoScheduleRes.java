package com.lawencon.leaf.community.pojo.schedule;

import java.time.LocalDate;

public class PojoScheduleRes {
	public String id;
	public LocalDate scheduleDate;
	public Integer ver;
	
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

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
}
