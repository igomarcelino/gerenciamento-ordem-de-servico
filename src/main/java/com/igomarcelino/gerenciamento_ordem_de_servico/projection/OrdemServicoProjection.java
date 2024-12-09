package com.igomarcelino.gerenciamento_ordem_de_servico.projection;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusOrdem;
import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.StatusPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrdemServicoProjection {

    Integer getId();

    Integer getFuncionario_id();

    Integer getCliente_id();

    Integer getPagamento_id();

    BigDecimal getValor();

    StatusOrdem getStatusOrdem();

    StatusPagamento getStatusPagamento();

    LocalDate getVencimento();
}
