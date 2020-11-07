package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "COMUNIDADE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comunidade {

    @EmbeddedId
    private ComunidadeId id;

    @Column(name = "VALOR")
    private Long valor;

}
