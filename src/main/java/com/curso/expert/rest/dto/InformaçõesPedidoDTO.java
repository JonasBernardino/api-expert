package com.curso.expert.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformaçõesPedidoDTO {
    private Integer id;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InformaçõesItemPedidoDTO> items;

}
