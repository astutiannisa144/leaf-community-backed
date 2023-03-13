package com.lawencon.leaf.community.pojo.polling;

import java.math.BigDecimal;

public class PollingDetailRes {

	private String pollingDetailId;
	private String content;
	private BigDecimal percentage;

	public String getPollingDetailId() {
		return pollingDetailId;
	}

	public void setPollingDetailId(String pollingDetailId) {
		this.pollingDetailId = pollingDetailId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

}
