package com.example.learning_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationFailure(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e -> error.put(e.getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> idNotFound(IdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
