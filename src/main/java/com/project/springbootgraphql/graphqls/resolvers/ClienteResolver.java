package com.project.springbootgraphql.graphqls.resolvers;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.models.Compra;
import com.project.springbootgraphql.services.CompraService;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteResolver implements GraphQLResolver<Cliente> {

    private final CompraService compraService;

    public List<Compra> compras(Cliente cliente) {
        return compraService.findAllByCliente(cliente);
    }
}
