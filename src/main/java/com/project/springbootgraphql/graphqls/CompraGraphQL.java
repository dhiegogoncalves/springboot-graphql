package com.project.springbootgraphql.graphqls;

import java.util.Date;
import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.project.springbootgraphql.graphqls.dtos.CompraResumoDTO;
import com.project.springbootgraphql.graphqls.inputs.CompraInput;
import com.project.springbootgraphql.models.Compra;
import com.project.springbootgraphql.services.ClienteService;
import com.project.springbootgraphql.services.CompraService;
import com.project.springbootgraphql.services.ProdutoService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final CompraService compraService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    public List<Compra> compras(int page, int size) {
        return compraService.findAll(PageRequest.of(page, size));
    }

    public Compra compra(Long id) {
        return compraService.findById(id);
    }

    public Compra saveCompra(CompraInput compraInput) {
        Compra compra = modelMapper.map(compraInput, Compra.class);
        compra.setData(new Date());
        compra.setCliente(clienteService.findById(compraInput.getClienteId()));
        compra.setProduto(produtoService.findById(compraInput.getProdutoId()));

        return compraService.save(compra);
    }

    public Compra updateCompra(CompraInput compraInput) {
        Compra compra = modelMapper.map(compraInput, Compra.class);
        compra.setCliente(clienteService.findById(compraInput.getClienteId()));
        compra.setProduto(produtoService.findById(compraInput.getProdutoId()));

        return compraService.update(compra);
    }

    public Boolean deleteCompra(Long id) {
        return compraService.deleteById(id);
    }

    public List<CompraResumoDTO> comprasResumo() {
        return compraService.findAllComprasResumo();
    }
}