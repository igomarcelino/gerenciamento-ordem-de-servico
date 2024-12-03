package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.OrdemServicoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM ORDEM_SERVICO
            WHERE STATUS_ORDEM = :statusOrdem
            ORDER BY ID;
            """)
    List<OrdemServicoProjection> findByStatus(String statusOrdem);
}
