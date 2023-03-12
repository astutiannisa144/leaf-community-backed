package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_comment")
public class Comment extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@Column(columnDefinition = "TEXT")
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
