package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.ErrorResponse;
import com.bgjshop.backend.exceptions.BadRequestException;
import com.bgjshop.backend.exceptions.DuplicateEntityException;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleDuplicateEntityExists(DuplicateEntityException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(500)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}


