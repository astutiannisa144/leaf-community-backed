package com.lawencon.leaf.community.pojo.social.media;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;

public class PojoSocialMediaReq {

	private String socialMediaName;
	private String socialMediaLink;
	private PojoFileReqInsert file;
	private Integer ver;
	private String id;
	
	
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSocialMediaName() {
		return socialMediaName;
	}
	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}
	public String getSocialMediaLink() {
		return socialMediaLink;
	}
	public void setSocialMediaLink(String socialMediaLink) {
		this.socialMediaLink = socialMediaLink;
	}
	public PojoFileReqInsert getFile() {
		return file;
	}
	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
	
}
