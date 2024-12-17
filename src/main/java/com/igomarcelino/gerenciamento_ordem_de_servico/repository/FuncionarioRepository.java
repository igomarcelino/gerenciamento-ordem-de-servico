package com.igomarcelino.gerenciamento_ordem_de_servico.repository;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Funcionario;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.FuncionarioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query(nativeQuery = true,value = """
            SELECT * FROM FUNCIONARIO
            WHERE CPF = :cpf;
            """)
    Optional<FuncionarioProjection> findByCPF(String cpf);

    Funcionario findByUsuarioLogin(String usuario);

    @Query(nativeQuery = true, value = """
            SELECT NOME FROM FUNCIONARIO F
            JOIN ORDEM_SERVICO OS ON OS.ID = F.ID
            WHERE F.ID  = :id
            """)
    String nomeFuncionario(Integer id);
}
