package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Service
@AllArgsConstructor
public class HttpResponseService {
    public HttpClientErrorException notFound(String message) {
        return HttpClientErrorException.create(message, HttpStatus.NOT_FOUND, message,HttpHeaders.EMPTY, null, null);
    }

    public HttpClientErrorException unauthorized(String message) {
        return HttpClientErrorException.create(message, HttpStatus.UNAUTHORIZED, message,HttpHeaders.EMPTY, null, null);
    }

    public HttpClientErrorException badRequest(String message) {
        return HttpClientErrorException.create(message, HttpStatus.BAD_REQUEST, message,HttpHeaders.EMPTY, null, null);
    }
}