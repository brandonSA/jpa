package com.demo.jpa.shared.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleViolatedException extends AppException {
  public BusinessRuleViolatedException(String rule) {
    super("Business rule violated: " + rule, HttpStatus.BAD_REQUEST, "RULE_VIOLATED");
  }
}
