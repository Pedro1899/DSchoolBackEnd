package com.Turq.DigitalSchool.exception;

public class ErrorDetail {
	
	private String Message;
	private String details;
	private Integer code;
	
	
	public ErrorDetail(String message, String details, Integer code) {
		super();
		Message = message;
		this.details = details;
		this.code = code;
	}


	public ErrorDetail() {
		super();
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
