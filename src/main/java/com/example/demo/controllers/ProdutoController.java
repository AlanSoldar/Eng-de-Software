package com.example.demo.controllers;

import com.example.demo.entities.Produto;
import com.example.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
public class ProdutoController extends BaseController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/produtos/page")
    public ResponseEntity getProdutos(@PageableDefault(page = 0, size = 5)
                                                     @SortDefault.SortDefaults({@SortDefault(sort = "nome", direction = Sort.Direction.ASC)}) Pageable pageable) {
        try {
            return ResponseEntity.ok().body(produtoService.findAllProdutos(pageable));
        } catch (
                HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

    }

    @GetMapping(value = "/produtos")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity getProdutos() {

        try {
            return ResponseEntity.ok().body(produtoService.findAllProdutos());
        } catch (
                HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

}
