package com.webflux.dto;

public class InputFailedValidationResponse {
	private int errorCode;
	private String message;
	private int input;
	public InputFailedValidationResponse() {
		
	}
	public InputFailedValidationResponse(int errorCode, String message,int input) {
		this.errorCode = errorCode;
		this.message = message;
		this.input = input;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
	}
	@Override
	public String toString() {
		return "InputFailedValidationResponse [errorCode=" + errorCode + ", message=" + message + "]";
	}
	
}
