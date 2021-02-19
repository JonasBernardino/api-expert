package com.curso.expert.rest.controller;

import com.curso.expert.domain.entity.Pedido;
import com.curso.expert.rest.dto.PedidoDTO;
import com.curso.expert.rest.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
