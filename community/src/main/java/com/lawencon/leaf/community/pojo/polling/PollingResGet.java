package com.lawencon.leaf.community.pojo.polling;

import java.time.LocalDateTime;
import java.util.List;

public class PollingResGet {

	private String pollingId;
	private String content;
	private LocalDateTime expired;
	private Long totalPolling;
	private String userPollingId;
	private List<PollingDetailRes> pollingDetail;

	public String getUserPollingId() {
		return userPollingId;
	}

	public void setUserPollingId(String userPollingId) {
		this.userPollingId = userPollingId;
	}

	public String getPollingId() {
		return pollingId;
	}

	public void setPollingId(String pollingId) {
		this.pollingId = pollingId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getExpired() {
		return expired;
	}

	public void setExpired(LocalDateTime expired) {
		this.expired = expired;
	}

	public List<PollingDetailRes> getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(List<PollingDetailRes> pollingDetail) {
		this.pollingDetail = pollingDetail;
	}

	public Long getTotalPolling() {
		return totalPolling;
	}

	public void setTotalPolling(Long totalPolling) {
		this.totalPolling = totalPolling;
	}

}
