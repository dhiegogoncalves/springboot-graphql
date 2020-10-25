package com.project.springbootgraphql.graphqls;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.project.springbootgraphql.graphqls.inputs.ProdutoInput;
import com.project.springbootgraphql.models.Produto;
import com.project.springbootgraphql.services.ProdutoService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    public List<Produto> produtos() {
        return produtoService.findAll();
    }

    public Produto produto(Long id) {
        return produtoService.findById(id);
    }

    public Produto saveProduto(ProdutoInput produtoInput) {
        Produto produto = modelMapper.map(produtoInput, Produto.class);
        return produtoService.save(produto);
    }

    public Produto updateProduto(ProdutoInput produtoInput) {
        Produto produto = modelMapper.map(produtoInput, Produto.class);
        return produtoService.update(produto);
    }

    public Boolean deleteProduto(Long id) {
        return produtoService.deleteById(id);
    }
}