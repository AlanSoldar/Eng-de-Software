package com.example.demo.services;

import com.example.demo.entities.Biblioteca;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    public List<Biblioteca> findBibliotecasByUsuarioId(Long id) {

        List<Biblioteca> bibliotecas = bibliotecaRepository.findById_UsuarioId(id);
        System.out.println("retornando todos os bibliotecas do usuario com id = " + id);

        if (bibliotecas.isEmpty()) {
            throw new NotFoundException("No bibliotecas were found");
        }

        return bibliotecas;

    }

    public List<Biblioteca> findAllBibliotecas() {

        List<Biblioteca> bibliotecaList = new ArrayList<>();
        bibliotecaRepository.findAll().forEach(biblioteca -> bibliotecaList.add(biblioteca));
        System.out.println("retornando todos os bibliotecas");

        if (bibliotecaList.isEmpty()) {
            throw new NotFoundException("No bibliotecas were found");
        }

        return bibliotecaList;

    }

    public Biblioteca findBibliotecaById(Long id) {

        Biblioteca biblioteca = bibliotecaRepository.findById(id).orElseThrow(() -> new NotFoundException("biblioteca not found"));
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
