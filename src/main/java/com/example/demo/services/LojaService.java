package com.example.demo.services;

import com.example.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private ProductService productService;

    public Page<Product> listProducts(Pageable page) {
        return productService.findAllProducts(page);
    }

}
