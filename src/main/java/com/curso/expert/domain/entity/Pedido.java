package com.curso.expert.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    private LocalDate dataPedido;

    @Column(precision = 2)
    private BigDecimal total;

    @OneToMany
    private Set<ItemPedido> itensPedido;

    public void setItens(List<ItemPedido> itemsPedido) {
    }
}
