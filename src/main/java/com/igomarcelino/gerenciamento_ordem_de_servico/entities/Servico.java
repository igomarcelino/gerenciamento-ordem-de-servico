package com.igomarcelino.gerenciamento_ordem_de_servico.entities;

import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoDTO;
import com.igomarcelino.gerenciamento_ordem_de_servico.dto.ServicoDTO.ServicoRequestDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valor;

    public Servico() {
    }

    public Servico(Integer id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico(ServicoRequestDTO servicoRequestDTO){
        this.descricao = servicoRequestDTO.getDescricao();
        this.valor = servicoRequestDTO.getValor();
    }

    public Servico(ServicoDTO servicoDTO){
        BeanUtils.copyProperties(servicoDTO,this);
    }

    public Servico(Servico servico) {
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
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
