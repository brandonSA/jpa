package com.demo.jpa.model;

import java.util.List;

public class Result<T> {
    private final T value;
    private final String error;
    private final boolean success;

    private Result(T value, String error, boolean success) {
        this.value = value;
        this.error = error;
        this.success = success;
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error, false);
    }

    public boolean isSuccess() { return success; }
    public T getValue() { return value; }
    public String getError() { return error; }

    // public Result<List<ProductResponseDTO>> orElse(Result<Object> failure) {
    //   // TODO Auto-generated method stub
    //   throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    // }
}
