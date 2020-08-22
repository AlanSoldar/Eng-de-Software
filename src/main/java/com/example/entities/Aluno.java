package com.example.entities;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno {

    @Id
    @NotNull
    Long id;

    @Column(name = "NOME_ALUNO")
    @Column(name = "ALUN_NOME")
    @NotNull
    private String nome;

    @Column(name = "ALUNO_MATRICULA")
    @NotNull
    private Long matricula;

}
