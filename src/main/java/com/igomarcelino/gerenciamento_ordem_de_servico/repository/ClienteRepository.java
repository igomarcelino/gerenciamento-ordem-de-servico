package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
