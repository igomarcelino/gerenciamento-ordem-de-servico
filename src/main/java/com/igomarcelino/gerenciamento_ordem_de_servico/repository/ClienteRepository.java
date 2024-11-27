package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ClienteEntity.Cliente;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.ClienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(nativeQuery = true, value = """
            SELECT *, * FROM CLIENTE C
            JOIN ENDERECO ON C.ENDERECO_ID = ENDERECO.ID
            WHERE C.CPF = :cpf
            """)
    Optional<ClienteProjection> findByCPF(String cpf);

}
