package com.lawencon.leaf.community.pojo.post;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.leaf.community.pojo.polling.PollingResGet;

public class PojoPostResGetAll {

	private String postId;
	private String title;
	private String content;
	private Boolean isPremium;
	private String categoryId;
	private String categoryName;
	private String memberId;
	private String fileId;
	private String fullName;
	private Long likeSum;
	private Long commentSum;
	private LocalDateTime createdAt;
	private String likeId;
	private String bookmarkId;
	private PollingResGet polling;
	private List<String> file;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

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

	public List<String> getFile() {
		return file;
	}

	public void setFile(List<String> file) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
