package com.lawencon.leaf.community.constant;

public enum PostPageType {
	PROFILE("profile"), 
	LIKE("like"), 
	BOOKMARK("bookmark");

	private String code;

	PostPageType(final String code) {

		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}
}
