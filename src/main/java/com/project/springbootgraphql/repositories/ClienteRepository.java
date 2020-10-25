package com.project.springbootgraphql.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import com.project.springbootgraphql.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<Cliente> findAll();
}
