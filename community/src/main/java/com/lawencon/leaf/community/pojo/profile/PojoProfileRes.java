package com.lawencon.leaf.community.pojo.profile;

import java.math.BigDecimal;
import java.util.List;

import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.pojo.job.PojoJobRes;
import com.lawencon.leaf.community.pojo.profile.social.media.PojoProfileSocialMediaRes;

public class PojoProfileRes {
	private String id;
	
	private String ver;
	
	private String isActive;
	
	private String fullName;

	private String phoneNumber;

	private String address;

	private PojoJobRes job;

	private PojoFileRes file;
	
	
	private List<PojoProfileSocialMediaRes> profileSocialMedia;
	
	private BigDecimal balance;

	
	public PojoFileRes getFile() {
		return file;
	}

	public void setFile(PojoFileRes file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PojoJobRes getJob() {
		return job;
	}

	public void setJob(PojoJobRes job) {
		this.job = job;
	}


	public List<PojoProfileSocialMediaRes> getProfileSocialMedia() {
		return profileSocialMedia;
	}

	public void setProfileSocialMedia(List<PojoProfileSocialMediaRes> profileSocialMedia) {
		this.profileSocialMedia = profileSocialMedia;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
	
	
}
