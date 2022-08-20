package com.webflux.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;


public class Response {
	private Date date=new Date();
	private int result;
	
	public Response(int result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response [date=" + date + ", result=" + result + "]";
	}
	
}
