package com.igomarcelino.gerenciamento_ordem_de_servico.projection;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Endereco;

public interface ClienteProjection {
     Integer getId();
     String getNome();
     String getCpf();
     String getTelefone();
     String getEmail();
}
