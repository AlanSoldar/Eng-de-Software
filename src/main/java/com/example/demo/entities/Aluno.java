package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @NotNull
    private Long id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "MATRICULA")
    @NotNull
    private Long matricula;

}
