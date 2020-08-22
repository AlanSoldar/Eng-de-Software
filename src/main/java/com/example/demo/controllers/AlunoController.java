package com.example.demo.controllers;

import com.example.demo.entities.Aluno;
import com.example.demo.services.AlunoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/aluno/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Aluno> getAluno(@PathVariable("id") Long id) {

        Aluno aluno = alunoService.findAlunoById(id);

        return ResponseEntity.ok().body(aluno);

    }

    @GetMapping(value = "/aluno/nome/{nome}")
    public ResponseEntity<List<Aluno>> getAluno(@PathVariable("nome") String nome) {

        List<Aluno> alunos = alunoService.findAlunoByName(nome);

        return ResponseEntity.ok().body(alunos);

    }

    @PostMapping(value = "/aluno")
    public ResponseEntity<List<Aluno>> postAluno(@RequestBody Aluno aluno) {

        alunoService.saveAluno(aluno);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "aluno/{id}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable("id") Long id) {

        alunoService.deleteAlunoById(id);

        return ResponseEntity.ok().build();

    }
}
