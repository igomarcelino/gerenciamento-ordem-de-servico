package com.igomarcelino.gerenciamento_ordem_de_servico.projection;

public interface EnderecoProjection {
    Long getId();
    String getRua();
    String getNumero();
    String getComplemento();
    String getBairro();
    String getCidade();
    String getEstado();
    Integer getCEP();
}
