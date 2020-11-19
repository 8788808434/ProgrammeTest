package com.example.task.util;

import java.io.Serializable;
import java.util.List;

public class BaseResponse<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String status;
	private String reasonCode;
	private String reasonText;
	private T responseObject;
	private String totalRecords;
	private List<T> responseListObject;
	private String jwt;

	public BaseResponse() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonText() {
		return reasonText;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getResponseListObject() {
		return responseListObject;
	}

	public void setResponseListObject(List<T> responseListObject) {
		this.responseListObject = responseListObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
