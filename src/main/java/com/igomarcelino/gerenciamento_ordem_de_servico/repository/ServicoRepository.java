package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.ServicoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    @Query(nativeQuery = true,value = """
            SELECT * FROM SERVICO
            WHERE LOWER(DESCRICAO) LIKE %:descricao%;
            """)
    Optional<List<ServicoProjection>> findBydescricao(String descricao);
}
