package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BibliotecaId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PRODUTO")
    private Long productId;

}
