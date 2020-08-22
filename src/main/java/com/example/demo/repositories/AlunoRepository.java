package com.example.demo.repositories;

import com.example.demo.entities.Aluno;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {
    public List<Aluno> findByNomeAndMatricula(String nome, Long matricula);
}
