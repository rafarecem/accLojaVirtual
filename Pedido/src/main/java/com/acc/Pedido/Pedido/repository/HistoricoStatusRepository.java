package com.acc.Pedido.Pedido.repository;

import com.acc.Pedido.Pedido.model.HistoricoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {
    List<HistoricoStatus> findByPedido_IdPedido(Long pedidoId);  // Usando "_IdPedido" para acessar o campo idPedido
}
