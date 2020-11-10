package com.example.demo.repositories;

import com.example.demo.entities.Interesse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresseRepository extends PagingAndSortingRepository<Interesse, Long> {
    List<Interesse> findById_DonoId(Long DonoId);
}
