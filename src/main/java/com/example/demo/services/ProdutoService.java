package com.example.demo.services;

import com.example.demo.entities.Produto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> findAllProducts(Pageable page) {

        Page<Produto> produtos = produtoRepository.findAll(page);
        System.out.println("retornando todos os produtos da pagina " + page.getPageNumber());

        if (produtos.isEmpty()) {
            throw new NotFoundException("No produtos were found");
        }

        return produtos;

    }

    public List<Produto> findAllProducts() {

        List<Produto> produtoList = new ArrayList<>();
        produtoRepository.findAll().forEach(produto -> produtoList.add(produto));
        System.out.println("retornando todos os produtos");

        if (produtoList.isEmpty()) {
            throw new NotFoundException("No produtos were found");
        }

        return produtoList;

    }

    public Produto findProductById(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("produto not found"));
        System.out.println("retornando produto com id = " + id.toString());

        return produto;

    }

    public List<Produto> findProductByName(String nome) {

        List<Produto> produtos = produtoRepository.findByNome(nome);
        System.out.println("retornando lista de produtos com o nome = " + nome);

        return produtos;

    }

    public void saveProduct(Produto produto) {

        produtoRepository.save(produto);
        System.out.println("produto salvo");

    }

    public void deleteProductById(Long id) {

        produtoRepository.deleteById(id);
        System.out.println("deletando produto com id = " + id.toString());

    }
}
