package com.example.personal_library_api.web.advice;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
