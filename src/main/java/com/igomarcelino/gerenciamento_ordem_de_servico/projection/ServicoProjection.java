package com.igomarcelino.gerenciamento_ordem_de_servico.projection;

import java.math.BigDecimal;

public interface ServicoProjection {
    Integer getId();
    String getDescricao();
    BigDecimal getValor();
}
