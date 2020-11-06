package com.example.demo.data_transfer_objects;

import com.example.demo.enums.TransacaoEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LojaDTO {

    private Long usuarioId;

    private Long produtoId;

}
