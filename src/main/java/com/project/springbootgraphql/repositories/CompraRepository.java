package com.project.springbootgraphql.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import com.project.springbootgraphql.graphqls.dtos.CompraResumoDTO;
import com.project.springbootgraphql.models.Cliente;
import com.project.springbootgraphql.models.Compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<Compra> findAllByCliente(Cliente cliente);

    @Query("select new com.project.springbootgraphql.graphqls.dtos.CompraResumoDTO(c.id, cli.nome, p.nome, c.quantidade) from Compra c inner join c.cliente cli inner join c.produto p")
    List<CompraResumoDTO> findAllComprasResumo();
}
