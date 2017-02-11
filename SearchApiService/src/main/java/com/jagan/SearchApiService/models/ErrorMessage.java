package com.jagan.SearchApiService.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String message;
	private String code;
	private String documentation;

	public ErrorMessage() {
	}

	public ErrorMessage(String code, String message, String documentation) {
		super();
		this.code = code;
		this.message = message;
		this.documentation = documentation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}
