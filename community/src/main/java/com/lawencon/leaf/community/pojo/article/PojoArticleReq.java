package com.lawencon.leaf.community.pojo.article;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;

public class PojoArticleReq {
	private String id;
	private String articleCode;
	private PojoFileReqInsert file;
	private String adminId;
	private String title;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArticleCode() {
		return articleCode;
	}
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
	public PojoFileReqInsert getFile() {
		return file;
	}
	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
	
	
	
}
