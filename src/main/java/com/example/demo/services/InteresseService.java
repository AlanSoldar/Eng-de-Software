package com.example.demo.services;

import com.example.demo.data_transfer_objects.InteresseDTO;
import com.example.demo.entities.Interesse;
import com.example.demo.repositories.InteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InteresseService {
    @Autowired
    private InteresseRepository interesseRepository;

    public Page<Interesse> listInteresses(Pageable page) {
        return interesseRepository.findAll(page);

    }

    public void demonstrarInteresse(Interesse interesse){

        interesseRepository.save(interesse);
        System.out.println("interesse salvo");
    }
}
