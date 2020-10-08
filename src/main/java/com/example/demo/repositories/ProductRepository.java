package com.example.demo.repositories;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    public List<Product> findByNome(String nome);

}
