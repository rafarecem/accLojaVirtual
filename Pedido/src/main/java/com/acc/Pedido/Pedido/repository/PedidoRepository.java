package com.acc.Pedido.Pedido.repository;


import com.acc.Pedido.Pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
