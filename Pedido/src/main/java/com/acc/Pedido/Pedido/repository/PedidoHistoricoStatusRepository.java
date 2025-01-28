package com.acc.Pedido.Pedido.repository;


import com.acc.Pedido.Pedido.model.PedidoHistoricoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PedidoHistoricoStatusRepository extends JpaRepository<PedidoHistoricoStatus, Long> {
    List<PedidoHistoricoStatus> findByPedidoIdOrderByDataHoraStatusPedidoDesc(Long pedidoId);
    Optional<PedidoHistoricoStatus> findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(Long pedidoId);
}
