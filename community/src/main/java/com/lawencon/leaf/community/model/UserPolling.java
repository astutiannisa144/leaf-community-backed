package com.lawencon.leaf.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_polling", uniqueConstraints = {
		@UniqueConstraint(name = "user_polling_ck", columnNames = { "member_id", "polling_id" }) })
public class UserPolling extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "polling_detail_id", nullable = false)
	private PollingDetail pollingDetail;

	@OneToOne
	@JoinColumn(name = "polling_id", nullable = false)
	private Polling polling;

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public PollingDetail getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(PollingDetail pollingDetail) {
		this.pollingDetail = pollingDetail;
	}

	public Polling getPolling() {
		return polling;
	}

	public void setPolling(Polling polling) {
		this.polling = polling;
	}

}
