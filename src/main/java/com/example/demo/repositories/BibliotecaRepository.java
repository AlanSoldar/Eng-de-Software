package com.example.demo.repositories;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliotecaRepository extends PagingAndSortingRepository<Biblioteca, Long> {
    List<Biblioteca> findById_UsuarioId(Long usuarioId);

    List<Biblioteca> findById_ProdutoId(Long produtoId);

}
