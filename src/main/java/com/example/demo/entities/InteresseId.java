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
public class InteresseId implements Serializable {

    @Column(name="DONO_ID")
    private Long donoId;

    @Column(name="INTERESSADO_ID")
    private Long interessadoId;

    @Column(name="PRODUTO_ID")
    private Long produtoId;
}
