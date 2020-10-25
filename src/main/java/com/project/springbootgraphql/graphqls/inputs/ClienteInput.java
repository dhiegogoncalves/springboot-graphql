package com.project.springbootgraphql.graphqls.inputs;

import lombok.Data;

@Data
public class ClienteInput {
    private Long id;
    private String nome;
    private String email;
}
