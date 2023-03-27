package com.lawencon.leaf.community.pojo.article;

import java.time.LocalDateTime;

import com.lawencon.leaf.community.pojo.file.PojoFileRes;

public class PojoArticleRes {
	private String articleId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private String adminId;
	private String fullName;
	private String fileId;
	private Integer ver;
	private Boolean isActive;
	private PojoFileRes file;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public PojoFileRes getFile() {
		return file;
	}
	public void setFile(PojoFileRes file) {
		this.file = file;
	}
	
	
}
