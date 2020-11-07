package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;

@Entity(name = "INTERESSE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Interesse {

    @EmbeddedId
    private InteresseId id;
}
