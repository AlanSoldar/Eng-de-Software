package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "BIBLIOTECA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Biblioteca {

    @EmbeddedId
    private BibliotecaId id;

}
