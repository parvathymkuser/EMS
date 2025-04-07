package com.interview.ems.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
	public ApiResponse(String message, T data) {
		super();
		this.success=true;
		this.message = message;
		this.data = data;
	}
    
    
}
