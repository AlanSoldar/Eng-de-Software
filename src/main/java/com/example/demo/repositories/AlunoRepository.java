package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<User, Long> {
    public List<User> findByNome(String nome);
}
