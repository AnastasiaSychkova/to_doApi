package com.example.to_do_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class RestApiExceptions extends RuntimeException{
    @Getter
    private final Map<String, Object> parameters = new HashMap<>();

    @Getter
    private final HttpStatus status;

    public RestApiException(HttpStatus status) {
        this.status = status;
    }

    public RestApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        addParameter("message", message);
    }

    public RestApiException addParameter(String name, Object value) {
        parameters.put(name, value);
        return this;
    }
}
