package com.example.demo.services;

import com.example.demo.entities.Aluno;
import com.example.demo.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findAlunoById(Long id) {

        Aluno aluno = alunoRepository.findById(id).get();
        System.out.println("olá mundo");

        return aluno;

    }
}
