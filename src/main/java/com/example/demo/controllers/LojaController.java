package com.example.demo.controllers;

import com.example.demo.data_transfer_objects.LojaDTO;
import com.example.demo.services.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class LojaController extends BaseController {

    @Autowired
    private LojaService lojaService;

    @GetMapping(value = "/loja")
    public ResponseEntity getProdutos(@PageableDefault(page = 0, size = 20)
                                      @SortDefault.SortDefaults({@SortDefault(sort = "nome", direction = Sort.Direction.ASC)}) Pageable pageable) {
        try {
            return ResponseEntity.ok().body(lojaService.listProdutos(pageable));
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }
    }

    @PostMapping(value = "/loja")
    public ResponseEntity getProdutos(@RequestBody LojaDTO lojaDTO) {
        try {
            System.out.println("cheguei");
            lojaService.venderProdutoParaUsuario(lojaDTO);
        } catch (HttpClientErrorException exception) {
            return createResponseEntity(exception);
        }

        return ResponseEntity.ok().build();
    }

}
