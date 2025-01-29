package com.acc.Estoque.Estoque.repository;

import com.acc.Estoque.Estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProdutoId(Long produtoId);
}
