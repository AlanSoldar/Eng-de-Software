package com.example.demo.data_transfer_objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InteresseDTO {
    private Long interessadoId;

    private Long donoId;

    private Long produtoId;
}
