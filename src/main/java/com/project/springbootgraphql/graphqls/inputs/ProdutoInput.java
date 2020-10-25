package com.project.springbootgraphql.graphqls.inputs;

import lombok.Data;

@Data
public class ProdutoInput {
    private Long id;
    private String nome;
    private double valor;
}
