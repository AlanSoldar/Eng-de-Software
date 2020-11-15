package com.example.demo.repositories;

import com.example.demo.entities.Chat;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
    List<Chat> findById_Usuario1Id(Long usuario1Id);

    List<Chat> findById_Usuario2Id(Long usuario2Id);

    Optional<Chat> findById_Usuario1IdAndId_Usuario2Id(Long usuario1Id, Long usuario2Id);

}
