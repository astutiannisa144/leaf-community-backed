package com.lawencon.leaf.community.constant;

public enum EnumRole {
SA("SA"), AD("AD"), SY("SY"),MB("MB");
	
	private String code;

	EnumRole(final String code) {
		
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

}
