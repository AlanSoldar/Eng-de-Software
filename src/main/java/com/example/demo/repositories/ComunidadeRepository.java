package com.example.demo.repositories;

import com.example.demo.entities.Comunidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComunidadeRepository extends PagingAndSortingRepository<Comunidade, Long> {
    List<Comunidade> findById_UsuarioId(Long usuarioId);

    List<Comunidade> findById_ProdutoId(Long produtoId);

    Optional<Comunidade> findById_UsuarioIdAndId_ProdutoId(Long usuarioId, Long produtoId);

}
