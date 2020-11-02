package com.example.demo.controllers;

import com.example.demo.entities.Produto;
import com.example.demo.services.LojaService;
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

@Controller
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @GetMapping(value = "/loja")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Page<Produto>> getProducts(@PageableDefault(page = 0, size = 3)
                                                     @SortDefault.SortDefaults({@SortDefault(sort = "nome", direction = Sort.Direction.ASC)}) Pageable pageable) {

        Page<Produto> produtos = lojaService.listProducts(pageable);

        return ResponseEntity.ok().body(produtos);

    }

}
