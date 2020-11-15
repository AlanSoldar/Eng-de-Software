package com.example.demo.repositories;

import com.example.demo.entities.Interesse;
import com.example.demo.entities.InteresseId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteresseRepository extends PagingAndSortingRepository<Interesse, InteresseId> {

    Optional<Interesse> findByIdDonoIdAndIdInteressadoId(Long donoId, Long interessadoId);

    Optional<Interesse> findByIdDonoIdAndIdInteressadoIdAndIdProdutoId(Long donoId, Long interessadoId, Long produtoId);
}
