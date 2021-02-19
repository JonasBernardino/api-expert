package com.curso.expert.rest.service;

import com.curso.expert.domain.entity.Cliente;
import com.curso.expert.domain.entity.ItemPedido;
import com.curso.expert.domain.entity.Pedido;
import com.curso.expert.domain.entity.Produto;
import com.curso.expert.domain.repositorio.ClientesRepository;
import com.curso.expert.domain.repositorio.Item_pedidoRepository;
import com.curso.expert.domain.repositorio.PedidoRepository;
import com.curso.expert.domain.repositorio.ProdutoRepository;
import com.curso.expert.exception.RegraNegocioException;
import com.curso.expert.rest.dto.ItemPedidoDTO;
import com.curso.expert.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ClientesRepository clientesRepository;
    private final ProdutoRepository produtosRepository;
    private final Item_pedidoRepository itemsPedidoRepository;


    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}