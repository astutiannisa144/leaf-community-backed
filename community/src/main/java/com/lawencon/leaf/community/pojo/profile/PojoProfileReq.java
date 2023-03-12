package com.lawencon.leaf.community.pojo.profile;

import java.util.List;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;
import com.lawencon.leaf.community.pojo.job.PojoJobReq;
import com.lawencon.leaf.community.pojo.profile.social.media.PojoProfileSocialMediaReq;

public class PojoProfileReq {
	private String fullName;

	private String phoneNumber;

	private String address;

	private PojoJobReq job;

	private PojoFileReqInsert file;
	
	private List<PojoProfileSocialMediaReq> profileSocialMedia;

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

	public PojoJobReq getJob() {
		return job;
	}

	public void setJob(PojoJobReq job) {
		this.job = job;
	}

	public PojoFileReqInsert getFile() {
		return file;
	}

	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}

	public List<PojoProfileSocialMediaReq> getProfileSocialMedia() {
		return profileSocialMedia;
	}

	public void setProfileSocialMedia(List<PojoProfileSocialMediaReq> profileSocialMedia) {
		this.profileSocialMedia = profileSocialMedia;
	}


	
}
