package com.project.springbootgraphql.services;

import java.util.List;

import com.project.springbootgraphql.exceptions.ServiceException;
import com.project.springbootgraphql.graphqls.dtos.CompraResumoDTO;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.models.Compra;
import com.project.springbootgraphql.repositories.CompraRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;

    public List<Compra> findAll(PageRequest pageRequest) {
        return compraRepository.findAll(pageRequest).getContent();
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra update(Compra compra) {
        Compra compraDb = compraRepository.findById(compra.getId()).orElseThrow(() -> {
            throw new ServiceException("Não existe compra com esse ID.");
        });

        compraDb.setCliente(compra.getCliente());
        compraDb.setProduto(compra.getProduto());
        compraDb.setQuantidade(compra.getQuantidade());
        compraDb.setStatus(compra.getStatus());

        return compraRepository.save(compraDb);
    }

    public Boolean deleteById(Long id) {
        if (compraRepository.findById(id).isPresent()) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Compra> findAllByCliente(Cliente cliente) {
        return compraRepository.findAllByCliente(cliente);
    }

    public List<CompraResumoDTO> findAllComprasResumo() {
        return compraRepository.findAllComprasResumo();
    }
}
