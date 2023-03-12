package com.lawencon.leaf.community.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_activity", uniqueConstraints = {
		@UniqueConstraint(name = "user_activity_ck", columnNames = { "member_id", "activity_id" }) })
public class UserActivity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "member_id", nullable = false)
	private User member;

	@OneToOne
	@JoinColumn(name = "activity_id", nullable = false)
	private Activity activity;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@OneToOne
	@JoinColumn(name = "user_voucher_id")
	private UserVoucher userVoucher;

	private Boolean isApproved;
	private BigDecimal totalPrice;
	
	@Column(length=36)
	private String invoiceCode;
	
	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UserVoucher getUserVoucher() {
		return userVoucher;
	}

	public void setUserVoucher(UserVoucher userVoucher) {
		this.userVoucher = userVoucher;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	
}
