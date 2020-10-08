package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/aluno/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<User> getAluno(@PathVariable("id") Long id) {

        User user = userService.findAlunoById(id);

        return ResponseEntity.ok().body(user);

    }

    @GetMapping(value = "/aluno/nome/{nome}")
    public ResponseEntity<List<User>> getAluno(@PathVariable("nome") String nome) {

        List<User> users = userService.findAlunoByName(nome);

        return ResponseEntity.ok().body(users);

    }

    @PostMapping(value = "/aluno")
    public ResponseEntity<List<User>> postAluno(@RequestBody User user) {

        userService.saveAluno(user);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "aluno/{id}")
    public ResponseEntity<User> deleteAluno(@PathVariable("id") Long id) {

        userService.deleteAlunoById(id);

        return ResponseEntity.ok().build();

    }
}
