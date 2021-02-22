package com.curso.expert.rest.controller;

import com.curso.expert.domain.entity.ItemPedido;
import com.curso.expert.domain.entity.Pedido;
import com.curso.expert.domain.enums.StatusPedido;
import com.curso.expert.rest.dto.AtualizacaoStatusPedidoDTO;
import com.curso.expert.rest.dto.InformaçõesItemPedidoDTO;
import com.curso.expert.rest.dto.InformaçõesPedidoDTO;
import com.curso.expert.rest.dto.PedidoDTO;
import com.curso.expert.rest.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Integer> save (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return ResponseEntity.ok().body(pedido.getId());

    }

    @GetMapping("/{id}")
    public InformaçõesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService.obterPedidoCompleto(id).map(p -> converter(p)).orElseThrow(()->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformaçõesPedidoDTO converter(Pedido pedido){
        return InformaçõesPedidoDTO
                .builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformaçõesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(item -> InformaçõesItemPedidoDTO
        .builder().descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco_unitario())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }

}
