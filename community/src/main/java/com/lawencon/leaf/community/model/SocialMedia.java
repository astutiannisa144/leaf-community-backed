package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_social_media", uniqueConstraints = {
		@UniqueConstraint(name = "social_media_bk", columnNames = { "socialMediaCode" }) })
public class SocialMedia extends BaseEntity {

	@Column(nullable = false, length = 10)
	private String socialMediaCode;

	@Column(nullable = false, length = 30)
	private String socialMediaName;

	@Column(nullable = false, length = 30)
	private String socialMediaLink;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;
	
	@Column(nullable = false, length = 30)
	private String socialMediaIcon;
	
	
	public String getSocialMediaIcon() {
		return socialMediaIcon;
	}

	public void setSocialMediaIcon(String socialMediaIcon) {
		this.socialMediaIcon = socialMediaIcon;
	}

	public String getSocialMediaLink() {
		return socialMediaLink;
	}

	public void setSocialMediaLink(String socialMediaLink) {
		this.socialMediaLink = socialMediaLink;
	}

	public String getSocialMediaCode() {
		return socialMediaCode;
	}

	public void setSocialMediaCode(String socialMediaCode) {
		this.socialMediaCode = socialMediaCode;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
