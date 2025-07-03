package com.example.hotel_management_system.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private final String error;
    private final String message;

    public NotFoundException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
