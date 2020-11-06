package com.example.demo.data_transfer_objects;

import com.example.demo.enums.TransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    private Long valor;

    private Long numeroDoCartao;

    private TransacaoEnum transacao;

}
