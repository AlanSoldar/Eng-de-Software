package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TESTE", schema = "TST")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @NotNull
    private Long id;

    @Column(name = "NOME_ALUNO")
    @Column(name = "ALUN_NOME")
    @NotNull
    private String nome;

    @Column(name = "ALUNO_MATRICULA")
    @NotNull
    private Long matricula;

}
