package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "MATRICULA")
    @NotNull
    private Long matricula;

}
