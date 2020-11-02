package com.example.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class BibliotecaId implements Serializable {

    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    @Column(name = "PRODUTO_ID")
    private Long ProdutoId;

    public Long getProdutoId() {
        return ProdutoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
