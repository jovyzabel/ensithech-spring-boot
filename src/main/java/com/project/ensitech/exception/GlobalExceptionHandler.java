package com.project.ensitech.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationErrors(ConstraintViolationException e) {
        String msg = e.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.joining(" | "));
        return ResponseEntity.badRequest().body("Erreur de validation : " + msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.joining(" | "));
        return ResponseEntity.badRequest().body("Erreur de validation : " + msg);
    }
}