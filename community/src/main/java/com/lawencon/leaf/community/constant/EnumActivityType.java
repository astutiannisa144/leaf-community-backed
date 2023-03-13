package com.lawencon.leaf.community.constant;

public enum EnumActivityType {
	EV("EV"), CO("CO");
	private String code;

	EnumActivityType(final String code) {
		
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}
}
