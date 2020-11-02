package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "BIBLIOTECA")
@Data
public class Biblioteca {

    @EmbeddedId
    private BibliotecaId id;

    public BibliotecaId getId() {
        return id;
    }
}
