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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServicoRequestDTO that)) return false;

        if (getDescricao() != null ? !getDescricao().equals(that.getDescricao()) : that.getDescricao() != null)
            return false;
        return getValor() != null ? getValor().equals(that.getValor()) : that.getValor() == null;
    }

    @Override
    public int hashCode() {
        int result = getDescricao() != null ? getDescricao().hashCode() : 0;
        result = 31 * result + (getValor() != null ? getValor().hashCode() : 0);
        return result;
    }
}
