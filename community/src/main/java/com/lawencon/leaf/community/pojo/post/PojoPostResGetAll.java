package com.lawencon.leaf.community.pojo.post;

import java.util.List;

import com.lawencon.leaf.community.pojo.file.PojoFileRes;
import com.lawencon.leaf.community.pojo.polling.PollingResGet;

public class PojoPostResGetAll {

	private String postId;
	private String title;
	private String content;
	private Boolean isPremium;
	private String categoryId;
	private String memberId;
	private Long likeSum;
	private Long commentSum;
	private String createdAt;
	private PollingResGet polling;
	private List<PojoFileRes> file;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Long getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Long likeSum) {
		this.likeSum = likeSum;
	}

	public Long getCommentSum() {
		return commentSum;
	}

	public void setCommentSum(Long commentSum) {
		this.commentSum = commentSum;
	}

	public PollingResGet getPolling() {
		return polling;
	}

	public void setPolling(PollingResGet polling) {
		this.polling = polling;
	}

	public List<PojoFileRes> getFile() {
		return file;
	}

	public void setFile(List<PojoFileRes> file) {
		this.file = file;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
