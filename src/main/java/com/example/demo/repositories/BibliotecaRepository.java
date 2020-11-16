package com.example.demo.repositories;

import com.example.demo.entities.Biblioteca;
import com.example.demo.entities.BibliotecaId;
import com.example.demo.entities.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BibliotecaRepository extends PagingAndSortingRepository<Biblioteca, BibliotecaId> {
    List<Biblioteca> findByIdUsuarioId(Long usuarioId);

    List<Biblioteca> findById_ProdutoId(Long produtoId);

    Optional<Biblioteca> findByIdUsuarioIdAndIdProdutoId(Long usuarioId, Long produtoId);

}
