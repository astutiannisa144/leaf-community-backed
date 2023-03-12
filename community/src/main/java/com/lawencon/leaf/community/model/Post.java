package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post", uniqueConstraints = { @UniqueConstraint(name = "post_bk", columnNames = { "postCode" }) })
public class Post extends BaseEntity {

	@Column(nullable = false)
	private String postCode;

	@Column(length = 30)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private Boolean isPremium;
	
	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;
	
	@OneToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToOne
	@JoinColumn(name = "polling_id")
	private Polling polling;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}



	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Polling getPolling() {
		return polling;
	}

	public void setPolling(Polling polling) {
		this.polling = polling;
	}

}
