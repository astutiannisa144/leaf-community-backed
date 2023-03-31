package com.lawencon.leaf.community.pojo.post;

import java.util.List;

import com.lawencon.leaf.community.pojo.file.PojoFileReqInsert;
import com.lawencon.leaf.community.pojo.polling.PollingReqInsert;

public class PojoPostReqInsert {

	private String title;
	private String content;
	private Boolean isPremium;
	private String categoryId;
	private PollingReqInsert polling;
	private List<PojoFileReqInsert> file;
	private String id;
	
	
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

	public PollingReqInsert getPolling() {
		return polling;
	}

	public void setPolling(PollingReqInsert polling) {
		this.polling = polling;
	}

	public List<PojoFileReqInsert> getFile() {
		return file;
	}

	public void setFile(List<PojoFileReqInsert> file) {
		this.file = file;
	}

}
