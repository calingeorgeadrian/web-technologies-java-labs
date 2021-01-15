package com.bgjshop.backend.exceptions;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String message) {
        super(message + " already exists");
    }
}
