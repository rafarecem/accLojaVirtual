package com.acc.Produto.Produto.DTO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoMessageDTO implements Serializable {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
    private LocalDateTime dataHoraEntrada;
}
