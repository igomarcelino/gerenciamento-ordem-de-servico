package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ServicoBelonging;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.ServicoBelongingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface ServicoBelongingRepository extends JpaRepository<ServicoBelonging, ServicoBelongingPK> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO SERVICO_BELONGING (ORDEM_ID, SERVICO_ID) VALUES (:ordemId, :servicoId)")
    void insertServicoBelonging(@Param("ordemId") Integer ordemId, @Param("servicoId") Integer servicoId);
}
