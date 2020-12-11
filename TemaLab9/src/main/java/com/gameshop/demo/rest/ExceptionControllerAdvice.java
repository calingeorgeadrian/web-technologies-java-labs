package com.gameshop.demo.rest;

import com.gameshop.demo.dto.ErrorResponse;
import com.gameshop.demo.exceptions.BadRequestException;
import com.gameshop.demo.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        log.debug("Entity not found" + ex);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(BadRequestException ex) {
        log.debug("Bad request" + ex);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.debug("Invalid request" + ex);

        String invalidFields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message("The following fields are invalid: " + invalidFields)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}