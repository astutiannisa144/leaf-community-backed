package com.lawencon.leaf.community.pojo.article;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;

public class PojoArticleReqUpdate {
	private String id;
	private String title;
	private String content;
	private Integer ver;
	private PojoFileReqInsert file;
	
	
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public PojoFileReqInsert getFile() {
		return file;
	}
	public void setFile(PojoFileReqInsert file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
