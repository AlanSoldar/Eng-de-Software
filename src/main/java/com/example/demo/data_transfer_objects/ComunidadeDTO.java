package com.example.demo.data_transfer_objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComunidadeDTO {

    private Long usuarioId;

    private Long produtoId;

    private Long valor;

}
