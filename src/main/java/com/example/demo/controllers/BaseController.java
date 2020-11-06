package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class BaseController {
    protected ResponseEntity<String> createResponseEntity(HttpClientErrorException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getLocalizedMessage());
    }

    protected ResponseEntity<String> createResponseEntity(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
