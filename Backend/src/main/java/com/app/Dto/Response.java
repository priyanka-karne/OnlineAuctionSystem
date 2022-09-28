package com.app.Dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response<T> implements Serializable{

private ResponseTypeError status;
	
	public ResponseTypeError getStatus() {
	return status;
}

public Response(ResponseTypeError status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

public void setStatus(ResponseTypeError status) {
	this.status = status;
}

	private T data;
	public static ResponseEntity<?> success(Object data) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "success");
		if(data != null)
			map.put("data", data);
		return ResponseEntity.ok(map);
	}
	
	public static ResponseEntity<?> error(Object err) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "error");
		if(err != null)
			map.put("error", err);
		return ResponseEntity.ok(map);
	}
	
	public static ResponseEntity<?> status(HttpStatus status) {
		return ResponseEntity.status(status).build();
	}



	public Response() {
		super();
	}

	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	
}
