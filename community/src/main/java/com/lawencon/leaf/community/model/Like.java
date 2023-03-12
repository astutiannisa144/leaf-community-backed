package com.lawencon.leaf.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_like", uniqueConstraints = {
		@UniqueConstraint(name = "like_ck", columnNames = { "member_id", "post_id" }) })
public class Like extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
