package com.acc.Vendedor.Vendedor.repository;


import com.acc.Vendedor.Vendedor.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}

