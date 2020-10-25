package com.project.springbootgraphql.graphqls.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraResumoDTO {

    private Long compraId;
    private String cliente;
    private String produto;
    private int quantidade;

}
