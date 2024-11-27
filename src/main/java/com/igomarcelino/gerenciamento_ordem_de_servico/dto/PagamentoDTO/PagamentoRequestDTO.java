package com.igomarcelino.gerenciamento_ordem_de_servico.dto.PagamentoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.Enum.FormaPagamento;

import java.math.BigDecimal;

public class PagamentoRequestDTO {
    private FormaPagamento formaPagamento;
    private BigDecimal valor;

    public PagamentoRequestDTO(FormaPagamento formaPagamento, BigDecimal valor) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }

    public PagamentoRequestDTO() {
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
