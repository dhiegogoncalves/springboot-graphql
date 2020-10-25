package com.project.springbootgraphql.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import com.project.springbootgraphql.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Override
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<Produto> findAll();
}
