package com.acc.Pedido.Pedido.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String produtoNome;

    @Column(nullable = false, length = 45)
    private String pedidoDescricao;

    @Column(nullable = false)
    private Double pedidoValor;

    @Column(nullable = false)
    private Integer pedidoQuantidade;

    @Column(nullable = false)
    private LocalDateTime pedidoDataHora;

    @Column(nullable = false)
    private Long vendedor_idVendedor;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPedidoDescricao() {
        return pedidoDescricao;
    }

    public void setPedidoDescricao(String pedidoDescricao) {
        if (pedidoDescricao == null || pedidoDescricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição do pedido não pode estar vazia.");
        }
        this.pedidoDescricao = pedidoDescricao;
    }

    public Double getPedidoValor() {
        return pedidoValor;
    }

    public void setPedidoValor(Double pedidoValor) {
        this.pedidoValor = pedidoValor;
    }

    public Integer getPedidoQuantidade() {
        return pedidoQuantidade;
    }

    public String getProdutoNome(){
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome){
        this.produtoNome=produtoNome;
    }

    public void setPedidoQuantidade(Integer pedidoQuantidade) {
        this.pedidoQuantidade = pedidoQuantidade;
    }

    public LocalDateTime getPedidoDataHora() {
        return pedidoDataHora;
    }

    public void setPedidoDataHora(LocalDateTime pedidoDataHora) {
        this.pedidoDataHora = pedidoDataHora;
    }

    public Long getVendedor_idVendedor() {
        return vendedor_idVendedor;
    }

    public void setVendedor_idVendedor(Long vendedor_idVendedor) {
        this.vendedor_idVendedor = vendedor_idVendedor;
    }
}
