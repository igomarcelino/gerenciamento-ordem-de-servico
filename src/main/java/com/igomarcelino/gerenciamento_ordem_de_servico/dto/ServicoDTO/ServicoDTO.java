package com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO;

import com.igomarcelino.gerenciamento_ordem_de_servico.entities.Servico;
import com.igomarcelino.gerenciamento_ordem_de_servico.projection.ServicoProjection;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

public class ServicoDTO {

    private Integer id;
    private String descricao;
    private BigDecimal valor;

    public ServicoDTO() {
    }

    public ServicoDTO(Servico servico){
        BeanUtils.copyProperties(servico, this);
    }

    public ServicoDTO(Integer id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public ServicoDTO(ServicoRequestDTO servicoRequestDTO){
        descricao = servicoRequestDTO.getDescricao();
        valor = getValor();
    }

    public ServicoDTO(ServicoProjection servicoProjection){
        id = servicoProjection.getId();
        descricao = servicoProjection.getDescricao();
        valor = servicoProjection.getValor();
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(o instanceof ServicoDTO that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getDescricao() != null ? getDescricao().equals(that.getDescricao()) : that.getDescricao() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescricao() != null ? getDescricao().hashCode() : 0);
        return result;
    }
}
