package com.example.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatId implements Serializable {

    @Column(name = "USUARIO_1_ID")
    private Long usuario1Id;

    @Column(name = "USUARIO_2_ID")
    private Long usuario2Id;

}
