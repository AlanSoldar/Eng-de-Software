package com.example.demo.controllers;

import com.example.demo.data_transfer_objects.ComunidadeDTO;
import com.example.demo.data_transfer_objects.InteresseDTO;
import com.example.demo.services.ComunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class ComunidadeController extends BaseController {

    @Autowired
    private ComunidadeService comunidadeService;

    @GetMapping(value = "/comunidade")
    public ResponseEntity getProdutos(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        try {
            return ResponseEntity.ok().body(comunidadeService.listProdutos(pageable));
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

    @PostMapping(value = "/comunidade")
    public ResponseEntity postProduto(@RequestBody ComunidadeDTO comunidadeDTO) {
        try {
            comunidadeService.publicarProdutoNaComunidade(comunidadeDTO);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/comunidade")
    public ResponseEntity deleteProduto(@RequestBody ComunidadeDTO comunidadeDTO) {
        try {
            comunidadeService.removerProdutoDaComunidade(comunidadeDTO);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();
    }

}



