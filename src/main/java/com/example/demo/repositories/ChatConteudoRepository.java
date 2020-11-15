package com.example.demo.repositories;

import com.example.demo.entities.ChatConteudo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatConteudoRepository extends PagingAndSortingRepository<ChatConteudo, Long> {
    List<ChatConteudo> findByUsuario1Id(Long usuario1Id);

    List<ChatConteudo> findByUsuario2Id(Long usuario2Id);

    List<ChatConteudo> findByUsuario1IdAndUsuario2Id(Long usuario1Id, Long usuario2Id);

}
