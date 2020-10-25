package com.project.springbootgraphql.services;

import java.util.List;

import com.project.springbootgraphql.exceptions.ServiceException;
import com.project.springbootgraphql.models.Produto;
import com.project.springbootgraphql.repositories.ProdutoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Produto produto) {
        Produto produtoDb = produtoRepository.findById(produto.getId()).orElseThrow(() -> {
            throw new ServiceException("Não existe produto com esse ID.");
        });

        produtoDb.setNome(produto.getNome());
        produtoDb.setValor(produto.getValor());
        return produtoRepository.save(produtoDb);
    }

    public Boolean deleteById(Long id) {
        if (produtoRepository.findById(id).isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
