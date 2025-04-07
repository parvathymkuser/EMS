package com.interview.ems.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

