package com.curso.expert.rest.service;

import com.curso.expert.domain.entity.Cliente;
import com.curso.expert.domain.repositorio.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClientesRepository clientesRepository;

    public Cliente findById (Integer id){
        Optional<Cliente> obj = clientesRepository.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Cliente> findAll(){
        return clientesRepository.findAll();
    }

    public Cliente create(Cliente obj){
        obj.setId(null);
        return clientesRepository.save(obj);
    }

    public Cliente update(Integer id, Cliente obj){
        Cliente newObj = findById(id);
        newObj.setNome(obj.getNome());
        newObj.setCpf(obj.getCpf());
        return clientesRepository.save(newObj);
    }

    public void delete(Integer id){
        clientesRepository.deleteById(id);
    }



}
