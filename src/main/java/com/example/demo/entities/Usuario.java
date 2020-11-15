package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity(name = "USUARIO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @SequenceGenerator(name="seq", initialValue=8, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "IDADE")
    @NotNull
    private Long idade;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "USUARIO")
    @NotNull
    private String usuario;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "SALDO")
    private Long saldo;

    @Column(name = "ENDERECO")
    private String endereco;

}
