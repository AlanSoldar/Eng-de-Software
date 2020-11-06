package com.example.demo.services;

import com.example.demo.exceptions.HttpResponseService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    protected HttpResponseService httpResponseService;
}
