package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Endereco;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.EnderecoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM ENDERECO E
            WHERE E.ID = :id
            """)
    Optional<EnderecoProjection> findByClienteId(Integer id);
}
