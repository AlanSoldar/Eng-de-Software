package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "PRODUTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "PRECO")
    @NotNull
    private Long preco;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;

    @Column(name = "USUARIO_ID")
    @NotNull
    private Long userId;

}
