package com.project.springbootgraphql.services;

import java.util.List;

import com.project.springbootgraphql.exceptions.ServiceException;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.repositories.ClienteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteDb = clienteRepository.findById(cliente.getId()).orElseThrow(() -> {
            throw new ServiceException("Não existe cliente com esse ID.");
        });

        clienteDb.setNome(cliente.getNome());
        clienteDb.setEmail(cliente.getEmail());
        return clienteRepository.save(clienteDb);
    }

    public Boolean deleteById(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
