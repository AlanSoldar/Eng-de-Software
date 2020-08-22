package com.example.demo.controllers;

import com.example.demo.entities.Aluno;
import com.example.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/aluno/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable("id") Long id) {

        System.out.println("olá mundo");

        Aluno aluno = alunoService.findAlunoById(id);

        return ResponseEntity.ok().body(aluno);

    }

    @GetMapping(value = "/aluno/nome/{nome}")
    public ResponseEntity<List<Aluno>> getAluno(@PathVariable("nome") String nome) {

        System.out.println("olá mundo");

        List<Aluno> alunos = alunoService.findAlunoByName(nome);

        return ResponseEntity.ok().body(alunos);

    }
}
