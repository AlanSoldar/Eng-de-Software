package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "CHAT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @EmbeddedId
    private ChatId id;

    @Column(name = "RESOLVIDO")
    private Boolean resolvidoFlag;

}
