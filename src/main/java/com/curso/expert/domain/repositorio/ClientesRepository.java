package com.curso.expert.domain.repositorio;


import com.curso.expert.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT p FROM Cliente p WHERE p.nome LIKE %?1%"
            +"OR p.nome LIKE %?1%")
    public List<Cliente> findByName(String name);

}
