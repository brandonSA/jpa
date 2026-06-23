package com.demo.jpa.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {

  private final boolean success;
  private final String message;
  private final T data;
  private final LocalDateTime timestamp;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .message("Operation successful")
        .data(data)
        .timestamp(LocalDateTime.now())
        .build();
  }
}
