package com.demo.jpa.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NotFoundException extends AppException {
  public NotFoundException(String resource, Object id) {
    super(resource + " not found: " + id, HttpStatus.NOT_FOUND, "NOT_FOUND");
  }
}
