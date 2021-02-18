package com.curso.expert.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dataPedido;

    @Column(precision = 2)
    private BigDecimal total;

    @OneToMany
    private Set<Item_pedido> itens_pedido;

    @ManyToOne
    private Cliente cliente;


    public Set<Item_pedido> getItens_pedido() {
        return itens_pedido;
    }

    public void setItens_pedido(Set<Item_pedido> itens_pedido) {
        this.itens_pedido = itens_pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
