package com.curso.expert.domain.entity;

import com.curso.expert.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany
    private List<ItemPedido> itens;

}
