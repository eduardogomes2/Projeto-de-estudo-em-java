package com.eduardo.estoque.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.estoque.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}