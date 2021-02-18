package com.curso.expert.rest.service;

import com.curso.expert.domain.entity.Cliente;
import com.curso.expert.domain.entity.Produto;
import com.curso.expert.domain.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById (Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto create(Produto obj){
        obj.setId(null);
        return produtoRepository.save(obj);
    }

    public Produto update(Integer id, Produto obj){
        Produto newObj = findById(id);
        newObj.setDescricao(obj.getDescricao());
        newObj.setPreco_unitario(obj.getPreco_unitario());
        return produtoRepository.save(newObj);
    }

    public void delete(Integer id){
        produtoRepository.deleteById(id);
    }
}
