package com.example.demo.services;

import com.example.demo.entities.Aluno;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findAlunoById(Long id) {

        Aluno aluno = alunoRepository.findById(id).orElseThrow(()-> new NotFoundException("aluno not found"));
        System.out.println("retornando aluno com id = " + id.toString());

        return aluno;

    }

    public List<Aluno> findAlunoByName(String nome) {

        List<Aluno> alunos = alunoRepository.findByNome(nome);
        System.out.println("retornando lista de alunos");

        return alunos;

    }

    public void saveAluno(Aluno aluno) {

        alunoRepository.save(aluno);
        System.out.println("aluno salvo");

    }

    public void deleteAlunoById(Long id) {

        alunoRepository.deleteById(id);
        System.out.println("deletando com id = " + id.toString());

    }
}
