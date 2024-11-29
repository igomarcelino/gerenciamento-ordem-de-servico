package com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO;

import java.math.BigDecimal;

public class ServicoRequestDTO {

    private String descricao;
    private BigDecimal valor;

    public ServicoRequestDTO() {
    }

    public ServicoRequestDTO(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
