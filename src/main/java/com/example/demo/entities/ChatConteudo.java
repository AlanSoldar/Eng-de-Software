package com.example.demo.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity(name = "CHAT_CONTEUDO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatConteudo {

    @Id
    @SequenceGenerator(name="chatseq", initialValue=30, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatseq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USUARIO_1_ID")
    @NotNull
    private Long usuario1Id;

    @Column(name = "USUARIO_2_ID")
    @NotNull
    private Long usuario2Id;

    @Column(name = "REMETENTE_ID")
    @NotNull
    private Long remetenteId;

    @Column(name = "MENSAGEM")
    @NotNull
    private String mensagem;

    @Column(name = "DATE_TIME")
    private Long dateTime;

}
