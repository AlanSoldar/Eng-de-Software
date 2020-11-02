package com.example.demo.repositories;

import com.example.demo.entities.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);

    Optional<Usuario> findByUsuarioAndPassword(String usuario, String password);
}
