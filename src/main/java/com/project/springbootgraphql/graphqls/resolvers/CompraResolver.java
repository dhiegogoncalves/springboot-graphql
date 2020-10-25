package com.project.springbootgraphql.graphqls.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.models.Compra;
import com.project.springbootgraphql.models.Produto;
import com.project.springbootgraphql.services.ClienteService;
import com.project.springbootgraphql.services.ProdutoService;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompraResolver implements GraphQLResolver<Compra> {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Cliente cliente(Compra compra) {
        return clienteService.findById(compra.getCliente().getId());
    }

    public Produto produto(Compra compra) {
        return produtoService.findById(compra.getProduto().getId());
    }
}
