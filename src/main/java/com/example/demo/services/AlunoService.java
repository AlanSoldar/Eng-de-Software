package com.example.demo.services;

import com.example.demo.entities.Aluno;
import com.example.demo.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findAlunoById(Long id) {

        Aluno aluno = alunoRepository.findById(id).get();
        System.out.println("retornando aluno com id = " + id.toString());

        return aluno;

    }

    public List<Aluno> findAlunoByName(String nome) {

        List<Aluno> alunos = alunoRepository.findByNome(nome);
        System.out.println("retornando lista de alunos");

        return alunos;

    }
}
