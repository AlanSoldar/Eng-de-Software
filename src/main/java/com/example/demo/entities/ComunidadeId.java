package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComunidadeId implements Serializable {

    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    @Column(name = "PRODUTO_ID")
    private Long produtoId;

}
