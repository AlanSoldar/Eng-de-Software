package com.example.demo.repositories;

import com.example.demo.entities.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    public List<Usuario> findByNome(String nome);
}
