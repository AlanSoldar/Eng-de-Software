package com.example.demo.services;

import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService extends BaseService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> findAllProdutos(Pageable page) {

        Page<Produto> produtos = produtoRepository.findAll(page);
        System.out.println("retornando todos os produtos da pagina " + page.getPageNumber());

        if (produtos.isEmpty()) {
            throw httpResponseService.notFound("No produtos were found");
        }

        return produtos;

    }

    public List<Produto> findAllProdutos() {

        List<Produto> produtoList = new ArrayList<>();
        produtoRepository.findAll().forEach(produto -> produtoList.add(produto));
        System.out.println("retornando todos os produtos");

        if (produtoList.isEmpty()) {
            throw httpResponseService.notFound("No produtos were found");
        }

        return produtoList;

    }

    public Produto findProdutoById(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> httpResponseService.notFound("produto not found"));
        System.out.println("retornando produto com id = " + id.toString());

        return produto;

    }

    public List<Produto> findProdutoByName(String nome) {

        List<Produto> produtos = produtoRepository.findByNome(nome);
        System.out.println("retornando lista de produtos com o nome = " + nome);

        return produtos;

    }

    public void saveProduto(Produto produto) {

        produtoRepository.save(produto);
        System.out.println("produto salvo");

    }

    public void deleteProdutoById(Long id) {

        produtoRepository.deleteById(id);
        System.out.println("deletando produto com id = " + id.toString());

    }
}
