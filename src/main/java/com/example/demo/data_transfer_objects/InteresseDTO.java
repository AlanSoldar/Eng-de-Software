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
    public Long donoId;

    public Long interessadoId;

    public Long produtoId;
}
