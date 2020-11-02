package com.example.demo.repositories;

import com.example.demo.entities.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    public List<Produto> findByNome(String nome);

}
