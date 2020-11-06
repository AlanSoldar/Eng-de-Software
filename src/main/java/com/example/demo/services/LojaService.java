package com.example.demo.services;

import com.example.demo.entities.Produto;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LojaService extends BaseService {

    @Autowired
    private ProdutoService produtoService;

    public Page<Produto> listProdutos(Pageable page) {
        return produtoService.findAllProdutos(page);

    }

}
