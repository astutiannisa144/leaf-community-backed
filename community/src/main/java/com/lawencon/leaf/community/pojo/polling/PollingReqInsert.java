package com.lawencon.leaf.community.pojo.polling;

import java.time.LocalDateTime;
import java.util.List;

public class PollingReqInsert {

	private String content;
	private LocalDateTime expired;
	private List<PollingDetailReqInsert> pollingDetail;

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

	public List<PollingDetailReqInsert> getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(List<PollingDetailReqInsert> pollingDetail) {
		this.pollingDetail = pollingDetail;
	}

}
