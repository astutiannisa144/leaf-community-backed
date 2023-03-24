package com.lawencon.leaf.community.pojo.social.media;

import com.lawencon.leaf.community.pojo.file.PojoFileRes;

public class PojoSocialMediaRes {
	private String id;
	private String socialMediaName;
	private String socialMediaLink;
	private String socialMediaCode;
	private String socialMediaIcon;
	private PojoFileRes file;
	private Integer ver;
	private Boolean isActive;
	
	
	public String getSocialMediaIcon() {
		return socialMediaIcon;
	}
	public void setSocialMediaIcon(String socialMediaIcon) {
		this.socialMediaIcon = socialMediaIcon;
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
	
	public PojoFileRes getFile() {
		return file;
	}
	public void setFile(PojoFileRes file) {
		this.file = file;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public String getSocialMediaCode() {
		return socialMediaCode;
	}
	public void setSocialMediaCode(String socialMediaCode) {
		this.socialMediaCode = socialMediaCode;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
