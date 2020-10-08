package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AlunoRepository alunoRepository;

    public User findAlunoById(Long id) {

        User user = alunoRepository.findById(id).orElseThrow(()-> new NotFoundException("aluno not found"));
        System.out.println("retornando aluno com id = " + id.toString());

        return user;

    }

    public List<User> findAlunoByName(String nome) {

        List<User> users = alunoRepository.findByNome(nome);
        System.out.println("retornando lista de alunos");

        return users;

    }

    public void saveAluno(User user) {

        alunoRepository.save(user);
        System.out.println("aluno salvo");

    }

    public void deleteAlunoById(Long id) {

        alunoRepository.deleteById(id);
        System.out.println("deletando com id = " + id.toString());

    }
}
