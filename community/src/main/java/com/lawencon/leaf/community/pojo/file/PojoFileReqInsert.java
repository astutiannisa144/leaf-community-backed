package com.lawencon.leaf.community.pojo.file;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class PojoFileReqInsert {

	private String fileContent;
	@NotNull(message = "Extension for file Required")
	@Length(max = 10, message = "length for file Extension too long")
	private String fileExtension;

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
