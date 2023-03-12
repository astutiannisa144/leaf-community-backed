package com.lawencon.leaf.community.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_activity", uniqueConstraints = {
		@UniqueConstraint(name = "activity_bk", columnNames = { "activityCode" }) })
public class Activity extends BaseEntity {

	@Column(nullable = false, length = 10)
	private String activityCode;

	@OneToOne
	@JoinColumn(name = "activityType_id", nullable = false)
	private ActivityType activityType;
	
	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;
	
	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToOne
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@Column(length = 50)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 30)
	private String provider;

	@Column(length = 30)
	private String locationAddress;

	
	private LocalTime timeStart;

	private LocalTime timeEnd;
	
	private BigDecimal price;

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}


	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	
	
}
