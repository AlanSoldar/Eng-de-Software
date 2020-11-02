package com.example.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "BIBLIOTECA")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Biblioteca {

    @EmbeddedId
    private BibliotecaId id;

}
