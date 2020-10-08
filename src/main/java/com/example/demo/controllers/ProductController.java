package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.LojaService;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products/page")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Page<Product>> getProducts(@PageableDefault(page = 0, size = 5)
                                                     @SortDefault.SortDefaults({@SortDefault(sort = "nome", direction = Sort.Direction.ASC)}) Pageable pageable) {

        Page<Product> products = productService.findAllProducts(pageable);

        return ResponseEntity.ok().body(products);

    }

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = productService.findAllProducts();

        return ResponseEntity.ok().body(products);

    }

}
