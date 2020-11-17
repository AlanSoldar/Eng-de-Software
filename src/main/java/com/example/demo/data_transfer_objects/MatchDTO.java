package com.example.demo.data_transfer_objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    private Long usuario1Id;

    private Long usuario2Id;

    private Long produto1Id;

    private Long produto2Id;

    private Boolean matchAceito;
}
