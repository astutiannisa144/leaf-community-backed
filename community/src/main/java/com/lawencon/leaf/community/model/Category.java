package com.lawencon.leaf.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_category", uniqueConstraints = {
		@UniqueConstraint(name = "category_bk", columnNames = { "categoryCode" }) })
public class Category extends BaseEntity {

	@Column(nullable = false, length = 10)
	private String categoryCode;

	@Column(nullable = false, length = 30)
	private String categoryName;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
