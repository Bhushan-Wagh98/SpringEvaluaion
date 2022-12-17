package com.nissan.common;

import org.springframework.http.HttpStatus;

public class APIResponse {

//	variable declaration
	private Integer status; // 200, 400, 404
	private Object data;
	private Object error; // error message

//	default
	public APIResponse() {
		this.status = HttpStatus.OK.value();
		this.data = data;
		this.error = error;
	}

//	getters and setters
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

}
