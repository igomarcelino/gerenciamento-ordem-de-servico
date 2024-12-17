package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.entities.OrdemServico;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.OrdemServicoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM ORDEM_SERVICO
            WHERE STATUS_ORDEM = :statusOrdem
            ORDER BY ID;
            """)
    Optional<List<OrdemServicoProjection>> findByStatus(String statusOrdem);

    @Query(nativeQuery = true, value = """
            SELECT  VALOR, OS.ID, OS.STATUS_ORDEM, OS.VENCIMENTO FROM ORDEM_SERVICO OS
                       JOIN CLIENTE C ON  C.ID = OS.CLIENTE_ID
                       WHERE C.CPF = :cpf;
            """)
    Optional<List<OrdemServicoProjection>> findByCpfCliente(String cpf);

    @Query(nativeQuery = true,value = """
            SELECT *FROM ORDEM_SERVICO OS
            WHERE OS.ORDEM_LOGIN = :ordemLogin
            AND OS.ORDEM_SENHA = :ordemSenha
            """)
    Optional<OrdemServico> findByOrdemLogin(String ordemLogin, String ordemSenha);


}
