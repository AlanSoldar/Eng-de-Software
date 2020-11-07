package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.repositories.BibliotecaRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaService extends BaseService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    public List<Biblioteca> findBibliotecasByUsuarioId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findById_UsuarioId(id);
        System.out.println("retornando todos os bibliotecas do usuario com id = " + id);

        if (bibliotecas.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecas;

    }

    public List<Biblioteca> findBibliotecasByProdutoId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findById_ProdutoId(id);
        System.out.println("retornando produto na biblioteca do usuario. produtoId = " + id);

        if (bibliotecas.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecas;

    }

    public List<Biblioteca> findAllBibliotecas() {

        List<Biblioteca> bibliotecaList = new ArrayList<>();
        bibliotecaRepository.findAll().forEach(biblioteca -> bibliotecaList.add(biblioteca));
        System.out.println("retornando todos os bibliotecas");

        if (bibliotecaList.isEmpty()) {
            throw httpResponseService.notFound("No bibliotecas were found");
        }

        return bibliotecaList;

    }

    public Biblioteca findBibliotecaById(Long id) {

        Biblioteca biblioteca = bibliotecaRepository.findById(id).orElseThrow(() -> httpResponseService.notFound("biblioteca not found"));
        System.out.println("retornando biblioteca com id = " + id.toString());

        return biblioteca;

    }

    public void saveBiblioteca(Biblioteca biblioteca) {

        bibliotecaRepository.save(biblioteca);
        System.out.println("biblioteca salvo");

    }

    public void deleteBibliotecaById(Long id) {

        bibliotecaRepository.deleteById(id);
        System.out.println("deletando biblioteca com id = " + id.toString());

    }
}
