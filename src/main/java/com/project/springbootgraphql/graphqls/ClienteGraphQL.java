package com.project.springbootgraphql.graphqls;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.project.springbootgraphql.graphqls.inputs.ClienteInput;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.services.ClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    public List<Cliente> clientes() {
        return clienteService.findAll();
    }

    public Cliente cliente(Long id) {
        return clienteService.findById(id);
    }

    public Cliente saveCliente(ClienteInput clienteInput) {
        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);
        return clienteService.save(cliente);
    }

    public Cliente updateCliente(ClienteInput clienteInput) {
        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);
        return clienteService.update(cliente);
    }

    public Boolean deleteCliente(Long id) {
        return clienteService.deleteById(id);
    }
}