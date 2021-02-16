package com.curso.expert.domain.repositorio;


import com.curso.expert.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


}
