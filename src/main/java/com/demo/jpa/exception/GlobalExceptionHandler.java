package com.demo.jpa.exception;

import com.demo.jpa.model.ErrorResponse;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(ex.getStatus())
        .body(new ErrorResponse(ex.getErrorCode(), ex.getMessage()));
  }

  @ExceptionHandler(BusinessRuleViolatedException.class)
  public ResponseEntity<ErrorResponse> handleBusinessRule(BusinessRuleViolatedException ex) {
    return ResponseEntity.status(ex.getStatus())
        .body(new ErrorResponse(ex.getErrorCode(), ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    String details = ex.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField() + ": " + err.getDefaultMessage())
        .collect(Collectors.joining(", "));
    return ResponseEntity.badRequest()
        .body(new ErrorResponse("VALIDATION_FAILED", details));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
    System.err.println("Unexpected error occurred: " + ex.getMessage());
    ex.printStackTrace();
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse("INTERNAL_ERROR", "An unexpected error occurred"));
  }
}
