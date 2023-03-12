package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_profile_social_media")
public class ProfileSocialMedia extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "social_media_id", nullable = false)
	private SocialMedia socialMedia;

	@OneToOne
	@JoinColumn(name = "profile_id", nullable = false)
	private Profile profile;

	@Column(length = 30, nullable = false)
	private String username;

	@Column(length = 60)
	private String profileLink;


	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}

}
